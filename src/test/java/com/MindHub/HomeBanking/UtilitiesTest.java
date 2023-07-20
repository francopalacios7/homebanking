package com.MindHub.HomeBanking;

import com.MindHub.HomeBanking.utils.Utilities;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class UtilitiesTest {
   /* @Test
    public void cardNumberIsCreated(){
        String cardNumber = Utilities.cardNumberGenerator();
        assertThat(cardNumber,is(not(emptyOrNullString())));
    }
    @Test
    public void cvvNumberIsCreated(){
        int cvv = Utilities.cvvGenerator();
        assertThat(cvv,is(notNullValue()));
    }
    @Test
    public void cvvSize(){
        Integer cvv = Utilities.cvvGenerator();
        assertThat(cvv.toString(),hasLength(3));
    }*/
}
