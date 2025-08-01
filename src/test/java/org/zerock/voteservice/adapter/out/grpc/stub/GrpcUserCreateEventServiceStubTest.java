package org.zerock.voteservice.adapter.out.grpc.stub;

import com.google.protobuf.Timestamp;
import domain.event.user.create.protocol.UserCacheEventResponse;
import domain.event.user.create.protocol.UserValidateEventResponse;
import io.grpc.Status;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.voteservice.BaseTestSettings;
import org.zerock.voteservice.tool.time.DateConverter;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class GrpcUserCreateEventServiceStubTest extends BaseTestSettings {

    @Autowired
    private GrpcUserCreateEventServiceStub stub;

    @Test
    void validateUser() {
        Long uid = 0L;
        String userHash = "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff";

        try {
            UserValidateEventResponse response = stub.validateUser(uid, userHash);
            
            log.info("UserHash: {} -> Validation: {}, Status: {}",
                    userHash, response.getValidation(), response.getStatus());

            assertNotNull(response);
        } catch (io.grpc.StatusRuntimeException e) {
            log.error(e.getMessage());

            if (e.getStatus().getCode() != Status.Code.UNAVAILABLE) {
                fail("Unexpected exception occurred during test: " + e.getMessage());
            }
        } catch (Exception e) {
            fail("Unexpected exception occurred during test: " + e.getMessage());
        }
    }

    @Test
    void cacheUser() {
        Long uid = 0L;
        String userHash = "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff";
        String gender = "M";
        Timestamp birthDate = DateConverter.toTimestamp(
                LocalDate.of(2000, 1, 1)
        );

        try {
            UserCacheEventResponse response = stub.cacheUser(uid, userHash, gender, birthDate);

            log.info("UserHash: {} -> Cached: {}, Status: {}",
                    userHash, response.getCached(), response.getStatus());

            assertNotNull(response);
        } catch (io.grpc.StatusRuntimeException e) {
            log.error(e.getMessage());

            if (e.getStatus().getCode() != Status.Code.UNAVAILABLE) {
                fail("Unexpected exception occurred during test: " + e.getMessage());
            }
        } catch (Exception e) {
            fail("Unexpected exception occurred during test: " + e.getMessage());
        }
    }
}