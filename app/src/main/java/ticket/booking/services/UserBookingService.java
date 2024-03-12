package ticket.booking.services;
import ticket.booking.entites.Train;
import ticket.booking.entites.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.util.UserServiceUtil;


public class UserBookingService {
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH = "path_to_users_json_file";

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public List<User> loadUsers() throws IOException {
        File usersFile = new File(USERS_PATH);
        if (usersFile.exists()) {
            userList = objectMapper.readValue(usersFile, new TypeReference<List<User>>() {});
        } else {
            userList = new ArrayList<>();
        }
        return userList;
    }

    public Boolean signUp(User user) {
        try {
            userList.add(user);
            saveUserListToFile();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public Boolean loginUser(String username, String password) {
        Optional<User> foundUser = userList.stream()
                .filter(user -> user.getName().equals(username) && user.getPassword().equals(password))
                .findFirst();
        return foundUser.isPresent();
    }

    public void fetchBookings(String username, String password) {
        Optional<User> foundUser = userList.stream()
                .filter(user -> user.getName().equals(username) && user.getPassword().equals(password))
                .findFirst();
        foundUser.ifPresent(User::printTickets);
    }
    public Boolean cancelBooking(String ticketId){
        // todo: Complete this function
        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        }catch(IOException ex){
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
}
