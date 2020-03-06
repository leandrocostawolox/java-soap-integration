package com.leandrocosta.soapintegration.services;

import com.leandrocosta.calculatorservice.wsdl.Add;
import com.leandrocosta.calculatorservice.wsdl.AddResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBException;

@Service
public class CalculatorService extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CalculatorService.class);

    public AddResponse getAddResult(Integer firstOperand, Integer secondOperand) throws JAXBException {
        Add request = new Add();
        request.setIntA(firstOperand);
        request.setIntB(secondOperand);
        log.info("Requesting result for " + firstOperand + " + " + secondOperand);
        WebServiceTemplate webServiceGatewaySupport = getWebServiceTemplate();

        // Set Add.class marshaller
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Add.class);
        webServiceGatewaySupport.setMarshaller(marshaller);

        // Set AddResponse.class unmarshaller
        Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setClassesToBeBound(AddResponse.class);
        webServiceGatewaySupport.setUnmarshaller(unmarshaller);

        SoapActionCallback soapActionCallback = new SoapActionCallback("http://tempuri.org/Add");
        AddResponse addResponse = (AddResponse) webServiceGatewaySupport
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request, soapActionCallback);
        return addResponse;
    }

}