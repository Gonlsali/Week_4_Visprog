package com.example.week4.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4.R
import com.example.week4.data.dummy_data
import com.example.week4.model.categories
import com.example.week4.model.products

@Composable
fun TokpedView(categoryList: List<categories>, productList: List<products>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Toko Pedia",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Start
            )

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "more",
                tint = Color.Black
            )
        }
        Image(
            painter = painterResource(id = R.drawable.tokped),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(vertical = 15.dp),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = "Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        LazyRow {
            items(categoryList) {
                CategoriesCard(
                    it,
                    Modifier
                        .height(180.dp)
                        .padding(start = 12.dp)
                        .width(120.dp)
                )
            }
        }
        Text(
            text = "Products",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 2.dp, top = 20.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ){
            items(productList){
                ProductCard(Modifier.padding(10.dp), it)
            }
        }
    }
}

@Composable
fun CategoriesCard(category: categories, modifier: Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = category.image_path),
                contentDescription = category.category_name,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(120.dp)
                    .padding(15.dp)
            )
            Text(
                text = category.category_name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "${category.number_of_items} Products",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun ProductCard(modifier: Modifier = Modifier, product: products) {
    val formatter = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.image_path),
                contentDescription = product.product_name,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(120.dp)
                    .padding(15.dp)
            )
            Text(
                text = product.product_name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = formatter
            )
            Text(
                text = "Rp. ${product.price}",
                fontSize = 12.sp,
                modifier = formatter
            )
            Text(
                text = "Kota ${product.location}",
                fontSize = 12.sp,
                modifier = formatter
            )
            Text(
                text = "${product.sold} Sold",
                fontSize = 12.sp,
                modifier = formatter
            )
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun TokpedPreview() {
    TokpedView(categoryList = dummy_data().get_data_tokopedia_category(), productList = dummy_data().get_data_tokopedia_product())
}