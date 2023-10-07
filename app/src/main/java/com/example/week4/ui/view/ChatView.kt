package com.example.week4.ui.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4.data.dummy_data
import com.example.week4.model.line_chat

@Composable
fun ChatView(chatList: List<line_chat>) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.Black)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Chats",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Other",
                tint = Color.White
            )
        }
        
        LazyColumn{
            items(chatList){
                ChatCard(
                    chatList = it,
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun ChatCard(chatList: line_chat, modifier: Modifier) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        TextButton(
            onClick = {
                Toast.makeText(context,"Name test Clicked", Toast.LENGTH_SHORT)
                .show()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(12.dp)
                        .size(50.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = chatList.userName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = chatList.chat,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                }

                Text(
                    text = chatList.timeStamp,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Preview (showBackground = true, showSystemUi = false)
@Composable
fun ChatPreview() {
    ChatView(chatList = dummy_data().get_data_line())
}