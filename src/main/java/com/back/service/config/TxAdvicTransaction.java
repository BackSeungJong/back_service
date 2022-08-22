package com.back.service.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * com.hyundai.console.config
 * TxAdvicTransaction.java
 * @Desc
 * @author gGeon
 * @since 2019. 9. 9.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2019. 9. 9.  gGeon          최초 생성
 *
 * </pre>
 */
@Aspect
@Configuration 
public class TxAdvicTransaction {

	@Autowired
	 private DataSource dataSource;

    /**
     *@Method Name : transactionManager
     *@Return Type: PlatformTransactionManager
     *@Desc :
     */
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


    /**
     *@Method Name : txAdvice
     *@Return Type: TransactionInterceptor
     *@Desc :
     */
    @Bean
    public TransactionInterceptor txAdvice() {
        MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setName("*");
        transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        transactionAttribute.setTimeout(60);
        source.setTransactionAttribute(transactionAttribute);
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager(), source);
        return txAdvice;
    }

    /**
     *@Method Name : txAdviceAdvisor
     *@Return Type: Advisor
     *@Desc : 
     */
    @Bean
    public Advisor txAdviceAdvisor() { 
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.back.service.service.*.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
 
}
