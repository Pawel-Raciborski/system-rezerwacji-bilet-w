package org.app.handler;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Set;

public class TimeHandler implements SOAPHandler<SOAPMessageContext> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
    private static final QName SERVER_TIME_HEADER = new QName("http://localhost:8080/time","ServerTime");
    @Override
    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        // Dodaj nagłówek tylko do wiadomości wychodzących (od serwera do klienta)
        if (outboundProperty) {
            try {
                SOAPMessage soapMessage = context.getMessage();
                SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnvelope.getHeader();

                if (soapHeader == null) {
                    soapHeader = soapEnvelope.addHeader();
                }

                LocalDateTime now = LocalDateTime.now();
                String formattedTime = now.format(FORMATTER) + " (Server Time - Warsaw)"; // Dodaj strefę czasową

                soapHeader.addHeaderElement(SERVER_TIME_HEADER).addTextNode(formattedTime);

                soapMessage.saveChanges();

            } catch (SOAPException e) {
                System.err.println("AddServerTimeHandler - Błąd podczas dodawania nagłówka czasu: " + e.getMessage());
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
