package com.android.dudupoo.placesparse.pojo.place;

/**
 * Created by rachnagoel on 16/11/16.
 */

public class Photo
{
    private double width;
    private String photo_reference;

    public Photo(double width, String photo_reference)
    {
        this.width = width;
        this.photo_reference = photo_reference;
    }

    @Override
    public String toString()
    {
        return "Photo{" +
                "width=" + width +
                ", photo_reference='" + photo_reference + '\'' +
                '}';
    }

    public double getWidth()
    {
        return width;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    public String getPhoto_reference()
    {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference)
    {
        this.photo_reference = photo_reference;
    }
}
