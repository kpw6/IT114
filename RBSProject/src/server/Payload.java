package server;
import java.io.Serializable;
import java.util.List;

public class Payload implements Serializable {

    /**
     * baeldung.com/java-serial-version-uid
     */
    private static final long serialVersionUID = -6687715510484845706L;

    private String clientName;

    public void setClientName(String s) {
	this.clientName = s;
    }

    public String getClientName() {
	return clientName;
    }
    
    private String otherClientName;

    public void setOtherClientName(String s) {
	this.otherClientName = s;
    }

    public String getOtherClientName() {
	return otherClientName;
    }

    private String message;

    public void setMessage(String s) {
	this.message = s;
    }

    public String getMessage() {
	return this.message;
    }

    private PayloadType payloadType;

    public void setPayloadType(PayloadType pt) {
	this.payloadType = pt;
    }

    public PayloadType getPayloadType() {
	return this.payloadType;
    }

    private int number;

    public void setNumber(int n) {
	this.number = n;
    }

    public int getNumber() {
	return this.number;
    }

    @Override
    public String toString() {
	return String.format("Type[%s], Number[%s], Message[%s]", getPayloadType().toString(), getNumber(),
		getMessage());
    }
    
    private int choice;
    
    public void setChoice(int c)
    {
    	this.choice = c;
    }
    
    public int getChoice()
    {
    	return this.choice;
    }
    
    private String results;
    
    public void setResults(String results) {
    	this.results = results;
    }
    public String getResults() {
    	return results;
    }
    private int decision;
    
    public void setDecision(int decision) {
    	this.decision = decision;
    }
    public int getDecision() {
    	return decision;
    }
    
    boolean isReady = false;
    
    public void setReady(boolean isReady) {
    	this.isReady = isReady;
    }
    public boolean getReady() {
    	return isReady;
    }
    
    int totalReady;
    
    public void setTotalReady(int totalReady) {
    	this.totalReady = totalReady;
    }
    public int getTotalReady() {
    	return totalReady;
    }
    
}