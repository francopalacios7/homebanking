package com.MindHub.HomeBanking;

import com.MindHub.HomeBanking.models.Card;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.CardRepository;
import com.MindHub.HomeBanking.utils.Utilities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class CardTest {
    @Autowired
    CardRepository cardRepository;
    @Test
    public void has16Numbers(){
        List<Card> cardNumber = cardRepository.findAll();
        assertThat(cardNumber,hasItem(hasProperty("number", hasLength(19))));
    }
    @Test
    public void existCard(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards,is(not(empty())));
    }
}
