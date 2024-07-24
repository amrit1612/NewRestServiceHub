import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(PaymentController.class)
class PaymentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void testSuccessfulPayment() throws Exception {
        // Setup mock service response
        given(paymentService.processPayment(any(PaymentRequest.class))).willReturn(new PaymentResponse("Success"));

        // Perform POST request and verify response
        mockMvc.perform(post("/payments/process")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":100,\"currency\":\"USD\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));
    }

    @Test
    void testPaymentFailureInvalidInput() throws Exception {
        // Setup mock service to throw exception for invalid input
        given(paymentService.processPayment(any(PaymentRequest.class))).willThrow(new PaymentProcessingException("Invalid input"));

        // Perform POST request and verify response
        mockMvc.perform(post("/payments/process")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":-100,\"currency\":\"USD\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid input")));
    }

    @Test
    void testPaymentServiceUnavailable() throws Exception {
        // Setup mock service to simulate service unavailability
        given(paymentService.processPayment(any(PaymentRequest.class))).willThrow(new ServiceUnavailableException("Service Unavailable"));

        // Perform POST request and verify response
        mockMvc.perform(post("/payments/process")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(content().string(containsString("Service Unavailable")));
    }
}