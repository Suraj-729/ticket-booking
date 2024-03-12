package ticket.booking.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
public class Ticket {
    private String ticketId;

    private String userId;

    private  String source;
    private String destination;

    private Date dateOfTravel;

    private Train train;

    public  Ticket() {}

    public Ticket(String ticketId, String userId, String source, String destination, String dateOfTravel, Train train){
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = java.sql.Date.valueOf(dateOfTravel); // Convert String date to Date object
        this.train = train;
    }

    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", ticketId, userId, source, destination, dateOfTravel);

    }
    public String getTicketId(){
        return ticketId;
    }

    public void  setTicketId(String ticketId){
        this.ticketId = ticketId;
    }

    public String getSource(){
      return   this.source = source;
    }
    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getDestination(){
        return destination;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public Date getDateOfTravel(){
        return dateOfTravel;
    }


    public void setDateOfTravel(Date dateOfTravel){
        this.dateOfTravel = dateOfTravel;
    }


    public Train getTrain(){
        return train;
    }

    public void setTrain(Train train){
        this.train = train;
    }

}
