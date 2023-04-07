package com.trubitsyna.homework.data

import android.os.Parcel
import android.os.Parcelable

data class PostIntentData(
    val id: String?,
    val name: String?,
    val date: String?,
    val imageUrl: String?,
    val mainText: String?,
    val likeCount: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(date)
        parcel.writeString(imageUrl)
        parcel.writeString(mainText)
        parcel.writeString(likeCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostIntentData> {
        override fun createFromParcel(parcel: Parcel): PostIntentData {
            return PostIntentData(parcel)
        }

        override fun newArray(size: Int): Array<PostIntentData?> {
            return arrayOfNulls(size)
        }
    }
}