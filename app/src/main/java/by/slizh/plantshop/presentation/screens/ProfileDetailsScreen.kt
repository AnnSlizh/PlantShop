package by.slizh.plantshop.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.plantshop.presentation.components.bars.CustomTopAppBar
import by.slizh.plantshop.presentation.components.textFields.ProfileTextField
import by.slizh.plantshop.presentation.viewModels.authorization.AuthState
import by.slizh.plantshop.presentation.viewModels.userDetails.UserDetailEvent
import by.slizh.plantshop.presentation.viewModels.userDetails.UserDetailsViewModel
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.Green
import by.slizh.plantshop.ui.theme.Red
import by.slizh.plantshop.ui.theme.White
import by.slizh.plantshop.ui.theme.mulishFamily


@Composable
fun ProfileDetailsScreen(
    navController: NavController,
    userDetailsViewModel: UserDetailsViewModel = hiltViewModel()
) {
    val state by userDetailsViewModel.userDetailState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 7.dp, start = 16.dp, end = 16.dp, bottom = 7.dp)
    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 15.dp, top = 16.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = "Profile Details",
//                fontSize = 18.sp,
//                fontFamily = mulishFamily,
//                fontWeight = FontWeight.Bold
//            )
//        }

        CustomTopAppBar(navController = navController)

        Spacer(modifier = Modifier.height(10.dp))

        if (state.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = Green
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        ProfileTextField(
            fieldLabel = "Email",
            value = state.user?.email ?: "",
            onValueChange = {

            },
            isError = false
        )

        ProfileTextField(
            fieldLabel = "Name",
            value = state.user?.name ?: "",
            onValueChange = { userDetailsViewModel.onUserDetailEvent(UserDetailEvent.OnNameChange(it)) },
            isError = false
        )

        ProfileTextField(
            fieldLabel = "Surname ",
            value = state.user?.surname ?: "",
            onValueChange = {
                userDetailsViewModel.onUserDetailEvent(
                    UserDetailEvent.OnSurnameChange(
                        it
                    )
                )
            },
            isError = false
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Black, contentColor = White),
            shape = RoundedCornerShape(50),
            onClick = { userDetailsViewModel.onUserDetailEvent(UserDetailEvent.SaveChanges) }) {
            Text(
                text = "Okay",
                fontSize = 18.sp,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Normal
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        state.error?.let {
            Text(
                text = it,
                color = Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }


        state.successMessage?.let {
            Text(
                text = it,
                color = Green,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

