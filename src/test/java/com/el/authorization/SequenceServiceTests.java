package com.el.authorization;

import com.el.authorization.service.SequenceService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class SequenceServiceTests extends UnitBaseTests {

    private static final Logger logger = LoggerFactory.getLogger(SequenceServiceTests.class);

    @Resource
    private SequenceService sequenceService;

    @Test
    public void sequenceGetNextValTest() {
        for (int i = 0; i < 10; i++) {
            Long userSeq = sequenceService.getNextVal("USER_SEQ");
            logger.info("USER_SEQ={}", userSeq);
        }
        for (int i = 0; i < 10; i++) {
            Long userSeq = sequenceService.getNextVal("ROLE_SEQ");
            logger.info("ROLE_SEQ={}", userSeq);
        }
    }
}
