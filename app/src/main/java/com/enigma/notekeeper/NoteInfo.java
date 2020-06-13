package com.enigma.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

public final class NoteInfo  implements Parcelable{
    private CourseInfo mCourse;
    private String mTitle;
    private String mText;

    public NoteInfo(CourseInfo course, String title, String text) {
        mCourse = course;
        mTitle = title;
        mText = text;
    }

    private NoteInfo(Parcel parcel) {
        mCourse = parcel.readParcelable(CourseInfo.class.getClassLoader());
        mTitle = parcel.readString();
        mText = parcel.readString();
    }

    public CourseInfo getCourse() {
        return mCourse;
    }

    public void setCourse(CourseInfo course) {
        mCourse = course;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    private String getCompareKey() {
        return mCourse.getCourseId().toUpperCase() + "\n" + mTitle + "\n" + mText;
    }//Shows how the note will be displayed on the activity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteInfo that = (NoteInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }//Will pass the actual notes to the activity

    @Override
    public int describeContents() {//used to handle special handling needs
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(mCourse, 0);//Because this is not a supported reference type in the Note Info class
        parcel.writeString(mTitle);
        parcel.writeString(mText);
    }

    //Parcels must be accessed in the same order they were written as they have no identifiers
    public static final Parcelable.Creator<NoteInfo> CREATOR = // Creates instances of the Note Info
            new Parcelable.Creator<NoteInfo>() {
                @Override
                public NoteInfo createFromParcel(Parcel parcel) {
                    return new NoteInfo(parcel);
                }

                @Override
                public NoteInfo[] newArray(int size) {//Creates an array of our type of the appropriate size
                    return new NoteInfo[size];
                }
            };
}
