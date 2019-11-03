package com.lendico;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void validateCurrencyConversion() {
        String url = "http://localhost:" + port + "/api/v1/generate-plan";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("{\n" +
                "  \"duration\": 24,\n" +
                "  \"loanAmount\": 5000,\n" +
                "  \"nominalRate\": 5,\n" +
                "  \"startDate\": \"2019-11-02T23:51:27Z\"\n" +
                "}", headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(url, request, String.class);
        assertThat(response.getBody()).isEqualTo(expected);
    }

    private String expected = "[{\"date\":\"2019-11-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"20.83\",\"principal\":\"198.53\",\"initialOutstandingPrincipal\":\"5000\",\"remainingOutstandingPricipal\":\"4801.47\"},{\"date\":\"2019-12-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"20.01\",\"principal\":\"199.35\",\"initialOutstandingPrincipal\":\"4801.47\",\"remainingOutstandingPricipal\":\"4602.12\"},{\"date\":\"2020-01-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"19.18\",\"principal\":\"200.18\",\"initialOutstandingPrincipal\":\"4602.12\",\"remainingOutstandingPricipal\":\"4401.94\"},{\"date\":\"2020-02-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"18.34\",\"principal\":\"201.02\",\"initialOutstandingPrincipal\":\"4401.94\",\"remainingOutstandingPricipal\":\"4200.92\"},{\"date\":\"2020-03-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"17.5\",\"principal\":\"201.86\",\"initialOutstandingPrincipal\":\"4200.92\",\"remainingOutstandingPricipal\":\"3999.06\"},{\"date\":\"2020-04-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"16.66\",\"principal\":\"202.7\",\"initialOutstandingPrincipal\":\"3999.06\",\"remainingOutstandingPricipal\":\"3796.36\"},{\"date\":\"2020-05-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"15.82\",\"principal\":\"203.54\",\"initialOutstandingPrincipal\":\"3796.36\",\"remainingOutstandingPricipal\":\"3592.82\"},{\"date\":\"2020-06-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"14.97\",\"principal\":\"204.39\",\"initialOutstandingPrincipal\":\"3592.82\",\"remainingOutstandingPricipal\":\"3388.43\"},{\"date\":\"2020-07-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"14.12\",\"principal\":\"205.24\",\"initialOutstandingPrincipal\":\"3388.43\",\"remainingOutstandingPricipal\":\"3183.19\"},{\"date\":\"2020-08-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"13.26\",\"principal\":\"206.1\",\"initialOutstandingPrincipal\":\"3183.19\",\"remainingOutstandingPricipal\":\"2977.09\"},{\"date\":\"2020-09-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"12.4\",\"principal\":\"206.96\",\"initialOutstandingPrincipal\":\"2977.09\",\"remainingOutstandingPricipal\":\"2770.13\"},{\"date\":\"2020-10-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"11.54\",\"principal\":\"207.82\",\"initialOutstandingPrincipal\":\"2770.13\",\"remainingOutstandingPricipal\":\"2562.31\"},{\"date\":\"2020-11-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"10.68\",\"principal\":\"208.68\",\"initialOutstandingPrincipal\":\"2562.31\",\"remainingOutstandingPricipal\":\"2353.63\"},{\"date\":\"2020-12-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"9.81\",\"principal\":\"209.55\",\"initialOutstandingPrincipal\":\"2353.63\",\"remainingOutstandingPricipal\":\"2144.08\"},{\"date\":\"2021-01-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"8.93\",\"principal\":\"210.43\",\"initialOutstandingPrincipal\":\"2144.08\",\"remainingOutstandingPricipal\":\"1933.65\"},{\"date\":\"2021-02-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"8.06\",\"principal\":\"211.3\",\"initialOutstandingPrincipal\":\"1933.65\",\"remainingOutstandingPricipal\":\"1722.35\"},{\"date\":\"2021-03-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"7.18\",\"principal\":\"212.18\",\"initialOutstandingPrincipal\":\"1722.35\",\"remainingOutstandingPricipal\":\"1510.17\"},{\"date\":\"2021-04-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"6.29\",\"principal\":\"213.07\",\"initialOutstandingPrincipal\":\"1510.17\",\"remainingOutstandingPricipal\":\"1297.1\"},{\"date\":\"2021-05-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"5.4\",\"principal\":\"213.96\",\"initialOutstandingPrincipal\":\"1297.1\",\"remainingOutstandingPricipal\":\"1083.14\"},{\"date\":\"2021-06-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"4.51\",\"principal\":\"214.85\",\"initialOutstandingPrincipal\":\"1083.14\",\"remainingOutstandingPricipal\":\"868.29\"},{\"date\":\"2021-07-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"3.62\",\"principal\":\"215.74\",\"initialOutstandingPrincipal\":\"868.29\",\"remainingOutstandingPricipal\":\"652.55\"},{\"date\":\"2021-08-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"2.72\",\"principal\":\"216.64\",\"initialOutstandingPrincipal\":\"652.55\",\"remainingOutstandingPricipal\":\"435.91\"},{\"date\":\"2021-09-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.36\",\"interest\":\"1.82\",\"principal\":\"217.54\",\"initialOutstandingPrincipal\":\"435.91\",\"remainingOutstandingPricipal\":\"218.37\"},{\"date\":\"2021-10-03T12:51:27Z\",\"borrowerPaymentAmount\":\"219.28\",\"interest\":\"0.91\",\"principal\":\"218.37\",\"initialOutstandingPrincipal\":\"218.37\",\"remainingOutstandingPricipal\":\"0\"}]";

}