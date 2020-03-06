package com.leandrocosta.soapintegration.services;

import com.leandrocosta.calculatorservice.wsdl.Add;
import com.leandrocosta.calculatorservice.wsdl.AddResponse;
import com.leandrocosta.soapintegration.utils.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Service
/**
 * Class in charge of the communication with the external service for each operation
 */
public class CalculatorService extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CalculatorService.class);

    /**
     * Method to communicate with an external calculator service and perform an add operation between
     * the firstOperand and the secondOperand
     * @param firstOperand
     * @param secondOperand
     * @return An {@link AddResponse} with the sum of both operands
     */
    public AddResponse getAddResult(final Integer firstOperand, final Integer secondOperand) {
        final Add request = new Add();
        request.setIntA(firstOperand);
        request.setIntB(secondOperand);
        log.info("Requesting result for " + firstOperand + " + " + secondOperand);
        final WebServiceTemplate webServiceGatewaySupport = getWebServiceTemplate();

        // Set Add.class marshaller
        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Add.class);
        webServiceGatewaySupport.setMarshaller(marshaller);

        // Set AddResponse.class unmarshaller
        final Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setClassesToBeBound(AddResponse.class);
        webServiceGatewaySupport.setUnmarshaller(unmarshaller);

        // Set the action to be performed
        final SoapActionCallback soapActionCallback = new SoapActionCallback(UrlUtil.ADD_SOAP_ACTION);

        // Get the response
        final AddResponse addResponse = (AddResponse) webServiceGatewaySupport
                .marshalSendAndReceive(UrlUtil.CALCULATOR, request, soapActionCallback);
        return addResponse;
    }

}