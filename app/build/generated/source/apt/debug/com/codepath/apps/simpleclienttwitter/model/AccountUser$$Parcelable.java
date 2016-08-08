
package com.codepath.apps.simpleclienttwitter.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2016-08-07T22:12-0700")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class AccountUser$$Parcelable
    implements Parcelable, ParcelWrapper<com.codepath.apps.simpleclienttwitter.model.AccountUser>
{

    private com.codepath.apps.simpleclienttwitter.model.AccountUser accountUser$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static AccountUser$$Parcelable.Creator$$0 CREATOR = new AccountUser$$Parcelable.Creator$$0();

    public AccountUser$$Parcelable(com.codepath.apps.simpleclienttwitter.model.AccountUser accountUser$$2) {
        accountUser$$0 = accountUser$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(accountUser$$0, parcel$$0, flags, new HashSet<Integer>());
    }

    public static void write(com.codepath.apps.simpleclienttwitter.model.AccountUser accountUser$$1, android.os.Parcel parcel$$1, int flags$$0, Set<Integer> identitySet$$0) {
        int identity$$0 = System.identityHashCode(accountUser$$1);
        parcel$$1 .writeInt(identity$$0);
        if (!identitySet$$0 .contains(identity$$0)) {
            identitySet$$0 .add(identity$$0);
            if (accountUser$$1 == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(1);
                parcel$$1 .writeString(accountUser$$1 .profile_image_url);
                parcel$$1 .writeString(accountUser$$1 .screenname);
                parcel$$1 .writeString(accountUser$$1 .username);
            }
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.codepath.apps.simpleclienttwitter.model.AccountUser getParcel() {
        return accountUser$$0;
    }

    public static com.codepath.apps.simpleclienttwitter.model.AccountUser read(android.os.Parcel parcel$$3, Map<Integer, Object> identityMap$$0) {
        com.codepath.apps.simpleclienttwitter.model.AccountUser accountUser$$3;
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$0 .containsKey(identity$$1)) {
            com.codepath.apps.simpleclienttwitter.model.AccountUser accountUser$$4 = ((com.codepath.apps.simpleclienttwitter.model.AccountUser) identityMap$$0 .get(identity$$1));
            if ((accountUser$$4 == null)&&(identity$$1 != 0)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return accountUser$$4;
        }
        if (parcel$$3 .readInt() == -1) {
            accountUser$$3 = null;
            identityMap$$0 .put(identity$$1, null);
        } else {
            com.codepath.apps.simpleclienttwitter.model.AccountUser accountUser$$5;
            identityMap$$0 .put(identity$$1, null);
            accountUser$$5 = new com.codepath.apps.simpleclienttwitter.model.AccountUser();
            identityMap$$0 .put(identity$$1, accountUser$$5);
            accountUser$$5 .profile_image_url = parcel$$3 .readString();
            accountUser$$5 .screenname = parcel$$3 .readString();
            accountUser$$5 .username = parcel$$3 .readString();
            accountUser$$3 = accountUser$$5;
        }
        return accountUser$$3;
    }

    public final static class Creator$$0
        implements Creator<AccountUser$$Parcelable>
    {


        @Override
        public AccountUser$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new AccountUser$$Parcelable(read(parcel$$2, new HashMap<Integer, Object>()));
        }

        @Override
        public AccountUser$$Parcelable[] newArray(int size) {
            return new AccountUser$$Parcelable[size] ;
        }

    }

}
