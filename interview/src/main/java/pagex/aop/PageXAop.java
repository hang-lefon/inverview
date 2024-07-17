package pagex.aop;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



@Component //把这个类放到IOC容器里面
@Aspect
public class PageXAop {

    // 定义切入点
    @Pointcut("@annotation(com.example.interview.annotations.PageX)")
    public void point() {}
    // 环绕通知
    @Around("point()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("执行目标方法之前123");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");

        if(ObjectUtil.isNotEmpty(pageNum) && ObjectUtil.isNotEmpty(pageSize) ){
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        }

        Object result = pjp.proceed(); //执行目标方法
        System.out.println("执行目标方法之后");
        return result;
    }
}
