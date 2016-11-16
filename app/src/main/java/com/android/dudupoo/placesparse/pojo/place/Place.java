package com.android.dudupoo.placesparse.pojo.place;

public class Place
{
    private String placeName;
    private double rating;
    private Photo placePhoto;

    public Place(String placeName, double rating, Photo placePhoto)
    {
        this.placeName = placeName;
        this.rating = rating;
        this.placePhoto = placePhoto;
    }

    @Override
    public String toString()
    {
        return "Place{" +
                "placeName='" + placeName + '\'' +
                ", rating=" + rating +
                ", placePhoto=" + placePhoto +
                '}';
    }

    public String getPlaceName()
    {
        return placeName;
    }

    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    public Photo getPlacePhoto()
    {
        return placePhoto;
    }

    public void setPlacePhoto(Photo placePhoto)
    {
        this.placePhoto = placePhoto;
    }
}
