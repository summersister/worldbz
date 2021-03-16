package com.richman.common.interceptor;

import com.richman.common.annotaion.MultipleTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Stack;

@Slf4j
@Aspect
@Component
public class MultipleTransactionAop {

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.richman.common.annotaion.MultipleTransaction)")
    public void MultipleTransaction() {

    }

    @Around("MultipleTransaction() && @annotation(annotation)")
    public Object mtAround(ProceedingJoinPoint pjp, MultipleTransaction annotation) throws Throwable {

        Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack = new Stack<DataSourceTransactionManager>();

        Stack<TransactionStatus> transactionStatuStack = new Stack<TransactionStatus>();

        try {

            if (!openTransaction(dataSourceTransactionManagerStack, transactionStatuStack, annotation)) {

                throw new RuntimeException("未指定事务管理器");

            }

            Object ret = pjp.proceed();

            commit(dataSourceTransactionManagerStack, transactionStatuStack);

            return ret;

        } catch (Throwable e) {

            rollback(dataSourceTransactionManagerStack, transactionStatuStack);

            throw e;

        }

    }

    private boolean openTransaction(Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,Stack<TransactionStatus> transactionStatuStack, MultipleTransaction multipleTransaction) {

        String[] transactionMangerNames = multipleTransaction.value();

        if (ArrayUtils.isEmpty(multipleTransaction.value())) {

            return false;

        }
        for (String beanName : transactionMangerNames) {

            DataSourceTransactionManager dataSourceTransactionManager = (DataSourceTransactionManager)applicationContext.getBean(beanName);

            TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionDefinition());

            transactionStatuStack.push(transactionStatus);

            dataSourceTransactionManagerStack.push(dataSourceTransactionManager);

        }
        return true;

    }

    private void commit(Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,Stack<TransactionStatus> transactionStatuStack) {

        while (!dataSourceTransactionManagerStack.isEmpty()) {

            dataSourceTransactionManagerStack.pop().commit(transactionStatuStack.pop());

        }

    }

    private void rollback(Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,Stack<TransactionStatus> transactionStatuStack) {

        while (!dataSourceTransactionManagerStack.isEmpty()) {

            dataSourceTransactionManagerStack.pop().rollback(transactionStatuStack.pop());

        }

    }

}
