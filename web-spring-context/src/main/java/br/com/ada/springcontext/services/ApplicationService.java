package br.com.ada.springcontext.services;

import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final InternalClass internalClass;
    private final ExternalClass externalClass;

    public ApplicationService(InternalClass internalClass, ExternalClass externalClass) {
        this.internalClass = internalClass;
        this.externalClass = externalClass;
    }

    public String getInternalMessage() {
        return internalClass.getInternalMessage();
    }

    public String getExternalMessage() {
        return externalClass.getExternalMessage();
    }

}
