package App.BusinessLayer.Pojo;

public class UserRatingPOJO {
    float rating;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public UserRatingPOJO() {}

    public UserRatingPOJO(float rating) {
        setRating(rating);
    }
}
