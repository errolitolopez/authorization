package com.el.authorization;

import com.el.authorization.domain.req.user.QueryUserReq;
import com.el.authorization.domain.rsp.Paged;
import com.el.authorization.domain.rsp.Response;
import com.el.authorization.domain.rsp.user.UserRsp;
import com.el.authorization.facade.UserFacade;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class MethodValidatedAnnotatedTests extends UnitBaseTests {

    private static final Logger logger = LoggerFactory.getLogger(MethodValidatedAnnotatedTests.class);

    @Resource
    private UserFacade userFacade;

    @Test
    public void methodSecuredAnnotatedTests() {
        QueryUserReq req = new QueryUserReq();
        Response<Paged<UserRsp>> query = userFacade.query(req);
        logger.info("query.result = {}", query.getResult());
    }
}
