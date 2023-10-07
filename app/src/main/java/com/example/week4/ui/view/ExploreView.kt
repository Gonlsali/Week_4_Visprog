package com.example.week4.ui.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week4.R
import com.example.week4.model.Explore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

@Composable
fun ExploreView() {
    val context = LocalContext.current
    val exploreUser = loadExploreData(context = context)

    Box{
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .background(Color.Black)
                .padding(bottom = 70.dp)
        ) {
            item(
                span = { GridItemSpan(3) }
            ) {
                Search()
            }

            items(exploreUser) {item ->
                Image(
                    painter = painterResource(id = getResourceIdForImage(item.content)),
                    contentDescription = item.content,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable {
                            Toast
                                .makeText(context, item.content, Toast.LENGTH_SHORT)
                                .show()
                        },
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.Black),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                NavigationBar("Home", icon = R.drawable.home)
                NavigationBar("Search", icon = R.drawable.search)
                NavigationBar("Post", icon = R.drawable.post)
                NavigationBar("Reels", icon = R.drawable.reels)
                NavigationBar("Account", icon = R.drawable.account)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search() {
    var search by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = search,
        onValueChange = { search = it},
        label = {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
                modifier = Modifier.size(21.dp)
            )
            Text(
                text = "Search",
                color = Color.White,
                modifier = Modifier.padding(start = 32.dp)
            )
        },
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .padding(horizontal = 8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            textColor = Color.White
        )
    )
}

@Composable
fun loadExploreData(context: Context): List<Explore> {
    val jsonFileName = "explore.json"
    val inputStream = context.assets.open(jsonFileName)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val jsonText = reader.readText()
    val itemType = object : TypeToken<List<Explore>>() {}.type
    return Gson().fromJson(jsonText, itemType)
}

@Preview (showSystemUi = false, showBackground = true)
@Composable
fun ExplorePreview() {
    ExploreView()
}