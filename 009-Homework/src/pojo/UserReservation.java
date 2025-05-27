package pojo;
import java.util.List;

public class UserReservation {
    private User user;
    private List<Integer> reservations;

    public UserReservation(User user, List<Integer> reservations) {
        this.user = user;
        this.reservations = reservations;
    }
    public UserReservation() {
        
    }
    public User getUser() {
        return user;
    }

    public List<Integer> getReservations() {
        return reservations;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setReservations(List<Integer> reservations) {
        this.reservations = reservations;
    }
}
