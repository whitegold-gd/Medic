package com.example.medic.Presentation.Repository.Network.Google.OATH;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.medic.DI.ServiceLocator;
import com.example.medic.Domain.Model.User;
import com.example.medic.MainActivity;
import com.example.medic.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class GoogleAuth {
    private static final String TAG = "GoogleAuth";
    int RC_SIGN_IN = 8;
    public GoogleSignInClient mGoogleSignInClient;

    public void init(Activity activity){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
    }

    public void getLastAuthInfo(MainActivity activity){
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(activity);
        if (acct != null) {
            String personEmail = acct.getEmail();
            System.out.println("EMAIL = " + personEmail);
            ((EditText) activity.findViewById(R.id.editTextTextEmailAddress)).setText(personEmail);
        }
    }

    public Intent getIntent(){
        return mGoogleSignInClient.getSignInIntent();
    }

    public boolean requestCodeCheck(int requestCode, int RC_SIGN_IN, Intent data){
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            return handleSignInResult(task);
        }
        return false;
    }

    private boolean handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            return true;
        } catch (ApiException e) {
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            return false;
        }
    }

    public void signOut(Activity activity) {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
        ServiceLocator.getInstance().getUser().setRole(User.Role.Guest);
    }
}