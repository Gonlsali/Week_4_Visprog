package com.example.week4.ui.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4.R
import com.example.week4.model.Feed
import com.example.week4.model.Story
import com.example.week4.model.Suggestion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// PUBLIC FUNCTION
@Composable // Return ID untuk Painter
fun getResourceIdForImage(imageFileName: String): Int {
    return LocalContext.current.resources.getIdentifier(
        imageFileName,
        "drawable",
        LocalContext.current.packageName
    )
}

@Composable // Load Data Object Explore dari Json File
fun loadStory(context: Context): List<Story> {
    val fileName = "story.json"
    val inputStream = context.assets.open(fileName)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val jsonText = reader.readText()
    val itemType = object : TypeToken<List<Story>>() {}.type
    return Gson().fromJson(jsonText, itemType)
}

@Composable // Load Data Object Suggestion dari Json File
fun loadSuggest(context: Context): List<Suggestion> {
    val fileName = "suggestion.json"
    val inputStream = context.assets.open(fileName)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val jsonText = reader.readText()
    val itemType = object : TypeToken<List<Suggestion>>() {}.type
    return Gson().fromJson(jsonText, itemType)
}

@Composable // Load Data Object Feed dari Json File
fun loadFeed(context: Context): List<Feed> {
    val fileName = "feed.json"
    val inputStream = context.assets.open(fileName)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val jsonText = reader.readText()
    val itemType = object : TypeToken<List<Feed>>() {}.type
    return Gson().fromJson(jsonText, itemType)
}

@Composable
fun InstagramView() {
    val context = LocalContext.current
    val feedData = loadFeed(context)
    val storyData = loadStory(context)

    Box {
        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            LazyColumn(
                modifier = Modifier
            ) {
                item {
                    Header(context)
                    StoryRow(storyData)
                }
                items(feedData) { item ->
                    FeedView(
                        feedUser = item,
                    )
                }
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

@Composable
fun FeedView(feedUser: Feed) {
    var textExpand by rememberSaveable { mutableStateOf(false) }

    val resourceId = getResourceIdForImage(imageFileName = feedUser.profilePicture)
    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = resourceId),
                    contentDescription = "profile picture",
                    Modifier
                        .clip(shape = CircleShape)
                        .height(38.dp)
                        .width(38.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = feedUser.username,
                modifier = Modifier
                    .padding(horizontal = 11.dp)
                    .weight(1f),
                fontSize = 15.sp,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                Icons.Filled.MoreVert,
                contentDescription = "more",
                modifier = Modifier
                    .size(24.dp),
                tint = Color.White
            )
        }

        Image(
            painter = painterResource(id = getResourceIdForImage(imageFileName = feedUser.feedContent)),
            contentDescription = "image content",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.width(140.dp)
            ) {
                val context = LocalContext.current
                if (feedUser.isLike) {
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Like Button", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.like),
                            contentDescription = "like",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                } else {
                    IconButton(onClick = {
                        Toast.makeText(context, "Liked Button", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.liked),
                            contentDescription = "liked",
                            tint = Color.Red,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                IconButton(onClick = {
                    Toast.makeText(context, "Comment Button", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.comment),
                        contentDescription = "comment",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = {
                    Toast.makeText(context, "Send Button", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.messanger),
                        contentDescription = "send",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            if (feedUser.isSaved) {
                val context = LocalContext.current
                IconButton(onClick = {
                    Toast.makeText(context, "Save Button", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "save",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            } else {
                val context = LocalContext.current
                IconButton(onClick = {
                    Toast.makeText(context, "Save Button", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.saved_light),
                        contentDescription = "save",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

        if (feedUser.like == 1) {
            Text(
                text = "${feedUser.like} Like",
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 8.dp)
            )
        } else {
            Text(
                text = "${NumberFormat.getNumberInstance(Locale.US).format(feedUser.like)} Likes",
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            )
        }

        TextButton(
            onClick = { textExpand = !textExpand },
            modifier = Modifier.padding(bottom = 10.dp),
            shape = RoundedCornerShape(0.dp)
        )
        {
            if (!textExpand) {
                ExpandCaption(
                    username = feedUser.username,
                    caption = feedUser.caption,
                    maxLine = 2
                )
            } else {
                ExpandCaption(
                    username = feedUser.username,
                    caption = feedUser.caption,
                    maxLine = 5000
                )
            }
        }
        var formattedDate by remember { mutableStateOf("") }

        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val sameYearFormat = SimpleDateFormat("MMMM d", Locale.US)
        val notSameYearFormat = SimpleDateFormat("MMMM d, yyyy", Locale.US)
        val parsedDate = inputDateFormat.parse(feedUser.date)

        if (parsedDate != null) {
            val calendar = Calendar.getInstance()
            calendar.time = parsedDate

            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val year = calendar.get(Calendar.YEAR)

            formattedDate = if (year == currentYear) {
                sameYearFormat.format(parsedDate)
            } else {
                notSameYearFormat.format(parsedDate)
            }
        } else {
            formattedDate = feedUser.date
        }

        Text(
            text = formattedDate,
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 10.dp)
        )

    }

    val regex = Regex("\\d+")
    fun getFeedIndex(Index: String): Int? {
        val result = regex.find(Index)
        return result?.value?.toIntOrNull()
    }

    val feedIndex = getFeedIndex(feedUser.feedContent)?.minus(1)

    val context = LocalContext.current
    val suggestionData = loadSuggest(context)

    if (feedIndex != null) {
        if (feedIndex % 6 == 0) {
            SuggestionRow(suggestionData = suggestionData)
        }
    }
}

@Composable
fun Header(context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_dark),
            contentDescription = "Logo",
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.like),
                    contentDescription = "Like",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                )
            }

            IconButton(
                onClick = {
                    Toast.makeText(context, "Direct Message", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.dm),
                    contentDescription = "dm",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
    }
}

@Composable
fun StoryRow(storyData: List<Story>) {
    LazyRow(
        modifier = Modifier.padding(bottom = 5.dp)
    ) {
        items(storyData) {
            StoryUser(
                storyUser = it
            )
        }
    }
}

@Composable
fun StoryUser(storyUser: Story, modifier: Modifier = Modifier) {
    val resourceId = getResourceIdForImage(imageFileName = storyUser.profilePicture)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val context = LocalContext.current
        TextButton(
            onClick = {
                Toast.makeText(context, "${storyUser.username} story", Toast.LENGTH_SHORT).show()
            },
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.story),
                        contentDescription = "Border IG",
                        modifier = Modifier
                            .size(90.dp),
                        contentScale = ContentScale.Crop
                    )

                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = "profile picture",
                        Modifier
                            .clip(shape = CircleShape)
                            .height(80.dp)
                            .width(80.dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }

        Text(
            text = storyUser.username,
            fontSize = 13.sp,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun NavigationBar(navigation: String, icon: Int) {
    val context = LocalContext.current
    IconButton(onClick = {
        Toast.makeText(context, navigation, Toast.LENGTH_SHORT).show()
    }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = navigation,
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun SuggestionCard(suggestedUser: Suggestion) {
    OutlinedCard(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(Color.Black)
    ) {
        Box {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd),
                tint = Color.White
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = getResourceIdForImage(imageFileName = suggestedUser.profilePicture)),
                    contentDescription = "profile picture",
                    Modifier
                        .clip(shape = CircleShape)
                        .height(110.dp)
                        .width(110.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = suggestedUser.username,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(text = "Follow", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun SuggestionRow(suggestionData: List<Suggestion>) {
    LazyRow(
        modifier = Modifier.padding(vertical = 5.dp)
    ) {
        items(suggestionData) {
            SuggestionCard(suggestedUser = it)
        }
    }
}

@Composable
fun ExpandCaption(username: String, caption: String, maxLine: Int) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = Bold, fontSize = 15.sp)) {
            append(username)
        }
        withStyle(style = SpanStyle(fontWeight = Normal, fontSize = 15.sp)) {
            append(" $caption")
        }
    }

    Text(
        text = text,
        maxLines = maxLine,
        textAlign = TextAlign.Justify,
        color = Color.White,
        overflow = TextOverflow.Ellipsis,
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InstagramPreview() {
    InstagramView()
}