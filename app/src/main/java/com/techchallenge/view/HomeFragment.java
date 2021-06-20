package com.techchallenge.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techchallenge.R;
import com.techchallenge.adapter.LikeAdapter;
import com.techchallenge.adapter.ProfileAdapter;
import com.techchallenge.interfaces.ProfileView;
import com.techchallenge.model.InviteModel;
import com.techchallenge.model.ProfileResponseModel;
import com.techchallenge.presenter.LoginPresenter;
import com.techchallenge.presenter.ProfilePresenter;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Response;

public class HomeFragment extends Fragment implements ProfileView {

    private ProfilePresenter presenter;
    private RecyclerView recyclerViewLikes, rvProfile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        presenter = new ProfilePresenter(this);
        presenter.getProfile();
        recyclerViewLikes = view.findViewById(R.id.rvLikes);
        rvProfile = view.findViewById(R.id.rvProfile);
    }

    //******************onSucessResponse************************//
    @Override
    public void onSuccess(Response<ProfileResponseModel> responseModel) {
        if (responseModel.isSuccessful() && responseModel.code() == 200) {
            if (responseModel.body() != null) {

                //******************ReplacingFragment************************//

                if (responseModel.body().likes != null) {
                    LikeAdapter adapter = new LikeAdapter(getContext(), responseModel.body().likes.profiles);
                    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                    recyclerViewLikes.setLayoutManager(layoutManager);
                    recyclerViewLikes.setAdapter(adapter);

                }

                //******************SettingInvitesAdapter************************//

                if (responseModel.body().invites != null) {
                    ProfileAdapter adapter = new ProfileAdapter(getContext(), responseModel.body().invites.profiles);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    rvProfile.setLayoutManager(layoutManager);
                    rvProfile.setAdapter(adapter);

                }

            }
        }

    }

    //******************onFailure************************//

    @Override
    public void onFailure(Throwable throwable) {

        if (throwable instanceof UnknownHostException) {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        } else if (throwable instanceof SocketTimeoutException) {
            Toast.makeText(getContext(), "Server is not responding. Please try again", Toast.LENGTH_SHORT).show();
        } else if (throwable instanceof ConnectException) {
            Toast.makeText(getContext(), "Failed to connect server", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
