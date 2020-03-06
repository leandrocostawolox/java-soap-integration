package com.leandrocosta.soapintegration.services;

import com.leandrocosta.calculatorservice.wsdl.Add;
import com.leandrocosta.calculatorservice.wsdl.AddResponse;
import com.leandrocosta.calculatorservice.wsdl.Divide;
import com.leandrocosta.calculatorservice.wsdl.DivideResponse;
import com.leandrocosta.calculatorservice.wsdl.Multiply;
import com.leandrocosta.calculatorservice.wsdl.MultiplyResponse;
import com.leandrocosta.calculatorservice.wsdl.Subtract;
import com.leandrocosta.calculatorservice.wsdl.SubtractResponse;
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
     * Private method to reuse the {@link WebServiceTemplate} generation
     * @param marshallerClass the class to be used for marshalling
     * @param unmarshallerClass the class to be used for unmarshalling
     * @return The generated {@link WebServiceTemplate}
     */
    private WebServiceTemplate getWebServiceTemplate(Class marshallerClass, Class unmarshallerClass) {
        final WebServiceTemplate webServiceTemplate = getWebServiceTemplate();

        // Set marshaller
        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(marshallerClass);
        webServiceTemplate.setMarshaller(marshaller);

        // Set unmarshaller
        final Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setClassesToBeBound(unmarshallerClass);
        webServiceTemplate.setUnmarshaller(unmarshaller);
        return webServiceTemplate;
    }

    /**
     * Method to communicate with an external calculator service and perform an add operation between
     * the firstOperand and the secondOperand
     * @param firstOperand
     * @param secondOperand
     * @return An {@link AddResponse} with the sum of both operands
     */
    public AddResponse getAdditionResult(final Integer firstOperand, final Integer secondOperand) {
        final Add request = new Add();
        request.setIntA(firstOperand);
        request.setIntB(secondOperand);
        log.info("Requesting result for " + firstOperand + " + " + secondOperand);
        final WebServiceTemplate webServiceGatewaySupport = this.getWebServiceTemplate(Add.class, AddResponse.class);

        // Set the action to be performed
        final SoapActionCallback soapActionCallback = new SoapActionCallback(UrlUtil.ADD_SOAP_ACTION);

        // Get the response
        final AddResponse addResponse = (AddResponse) webServiceGatewaySupport
                .marshalSendAndReceive(UrlUtil.CALCULATOR, request, soapActionCallback);
        return addResponse;
    }

    /**
     * Method to communicate with an external calculator service and perform a subtract operation between
     * the firstOperand and the secondOperand
     * @param firstOperand
     * @param secondOperand
     * @return A {@link SubtractResponse} with the subtraction of both operands
     */
    public SubtractResponse getSubtactionResult(final Integer firstOperand, final Integer secondOperand) {
        final Subtract request = new Subtract();
        request.setIntA(firstOperand);
        request.setIntB(secondOperand);
        log.info("Requesting result for " + firstOperand + " - " + secondOperand);
        final WebServiceTemplate webServiceGatewaySupport = this.
                getWebServiceTemplate(Subtract.class, SubtractResponse.class);

        // Set the action to be performed
        final SoapActionCallback soapActionCallback = new SoapActionCallback(UrlUtil.SUBTRACT_SOAP_ACTION);

        // Get the response
        final SubtractResponse subtractResponse = (SubtractResponse) webServiceGatewaySupport
                .marshalSendAndReceive(UrlUtil.CALCULATOR, request, soapActionCallback);
        return subtractResponse;
    }

    /**
     * Method to communicate with an external calculator service and perform a multiply operation between
     * the firstOperand and the secondOperand
     * @param firstOperand
     * @param secondOperand
     * @return A {@link MultiplyResponse} with the multiplication of both operands
     */
    public MultiplyResponse getMultiplicationResult(final Integer firstOperand, final Integer secondOperand) {
        final Multiply request = new Multiply();
        request.setIntA(firstOperand);
        request.setIntB(secondOperand);
        log.info("Requesting result for " + firstOperand + " * " + secondOperand);
        final WebServiceTemplate webServiceGatewaySupport = this.
                getWebServiceTemplate(Multiply.class, MultiplyResponse.class);

        // Set the action to be performed
        final SoapActionCallback soapActionCallback = new SoapActionCallback(UrlUtil.MULTIPLY_SOAP_ACTION);

        // Get the response
        final MultiplyResponse multiplyResponse = (MultiplyResponse) webServiceGatewaySupport
                .marshalSendAndReceive(UrlUtil.CALCULATOR, request, soapActionCallback);
        return multiplyResponse;
    }

    /**
     * Method to communicate with an external calculator service and perform a divide operation between
     * the firstOperand and the secondOperand
     * @param firstOperand
     * @param secondOperand
     * @return A {@link DivideResponse} with the division of both operands
     */
    public DivideResponse getDivisionResult(final Integer firstOperand, final Integer secondOperand) {
        final Divide request = new Divide();
        request.setIntA(firstOperand);
        request.setIntB(secondOperand);
        log.info("Requesting result for " + firstOperand + " / " + secondOperand);
        final WebServiceTemplate webServiceGatewaySupport = this.
                getWebServiceTemplate(Divide.class, DivideResponse.class);

        // Set the action to be performed
        final SoapActionCallback soapActionCallback = new SoapActionCallback(UrlUtil.DIVIDE_SOAP_ACTION);

        // Get the response
        final DivideResponse divideResponse = (DivideResponse) webServiceGatewaySupport
                .marshalSendAndReceive(UrlUtil.CALCULATOR, request, soapActionCallback);
        return divideResponse;
    }

}