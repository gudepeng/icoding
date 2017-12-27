package top.icoding.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import top.icoding.util.Page;


/**
* @author 我是金角大王
* @date 2017年11月20日 下午3:00:04
*/
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})})
public class PageInterceptor implements Interceptor {
	private static final String METHODNAME = "ByPage"; 
	/**
     * 默认ObjectFactory
     */
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    /**
     * 默认ObjectWrapperFactory
     */
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    /**
     * 默认ReflectorFactory
     */
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    /**
     * 被拦截的方法进入该方法中
     * */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler=(StatementHandler)invocation.getTarget();
		MetaObject metaObject=MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_REFLECTOR_FACTORY);
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		 
		//是否结果为BYpage
		if(mappedStatement.getId().endsWith(METHODNAME)){
			BoundSql boundSql=statementHandler.getBoundSql();
			//获取到执行的sql
			String sql =boundSql.getSql();
			String countSql="select count(1) from("+sql+") a";
			Connection connection=(Connection) invocation.getArgs()[0];
			PreparedStatement preparedStatement=connection.prepareStatement(countSql);
			ParameterHandler parameterHandler=(ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(preparedStatement);
			ResultSet res=preparedStatement.executeQuery();
			//获取到执行的参数
			Map<String,Object> parmeter=(Map<String, Object>) boundSql.getParameterObject();
			Page page =(Page) parmeter.get("page");
			if(res.next()){
				page.setTotalNumber(res.getInt(1));
			}
			String newSql=sql+" limit "+page.getDbIndex()+","+page.getDbNumber();
			metaObject.setValue("delegate.boundSql.sql", newSql);
		}
		return invocation.proceed();
	}
	/**
	 * 拦截器
	 * */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
