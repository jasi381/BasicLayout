package com.example.basiclayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.basiclayout.ui.theme.BasicLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = Color.White
                ) {
                    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
                        FullApp()
                    }

                }
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null, tint = Color.Black
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        ),
        placeholder = {
            Text("Search", color = Color.Black)
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(start = 5.dp, end = 5.dp)
    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable :Int,
    @StringRes text :Int,
    modifier :Modifier = Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription =null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),

            style = MaterialTheme.typography.body1,
            color = Color.Black,
            modifier = Modifier
                .paddingFromBaseline(
                    top= 24.dp,
                    bottom = 8.dp
                )

        )
    }
}
@Composable
fun FavouriteCardCollection(
    modifier :Modifier=Modifier,
    @DrawableRes drawable :Int,
    @StringRes text :Int
) {
    val color = "#ececec"
    val clr = Color(color.toColorInt())

    Card(
        modifier = modifier.wrapContentSize(),
        border = BorderStroke(1.dp, Color.Green),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = clr,
        elevation = 15.dp
    ) {
        Row(
            modifier = modifier
                .width(192.dp)
                .wrapContentHeight()
                .padding(all = 4.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds,
            )

            Text(
                text = stringResource(text),
                color = Color.Black,
                fontSize = 18.sp,
                modifier = modifier.padding(start = 10.dp,end= 2.dp)
            )
        }
    }
}

@Composable
fun AlignYourBodyRow(modifier: Modifier= Modifier
) {
    val color = "#ececec"
    val clr = Color(color.toColorInt())
    Card(shape = RoundedCornerShape(2.dp),
        backgroundColor = clr,
        modifier = modifier.padding(5.dp),
        elevation = 15.dp,
        border = BorderStroke(1.dp, Color.Black)
    ) {
        LazyRow(
            modifier = modifier.padding(top = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(alignYourBodyData) { item ->
                AlignYourBodyElement(drawable = item.drawable, text = item.text)
            }
        }
    }
}

@Composable
fun FavouriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    val color = "#ececec"
    val clr = Color(color.toColorInt())

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(120.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavouriteCardCollection(
                drawable = item.drawable, text = item.text, modifier = modifier
                    .wrapContentSize()
                    .background(Color.White)
            )
        }
    }
}

@Composable
fun HomeSection(
    modifier :Modifier = Modifier,
    @StringRes title :Int,
    content : @Composable () ->Unit
){
    Column(modifier) {
        Text(
            text = stringResource(id = title),
            fontSize = 25.sp,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }

}
@Composable
fun FullView(modifier: Modifier = Modifier){
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        SearchBar()
        Spacer(Modifier.height(16.dp))
        HomeSection(title = R.string.hall_of_fame) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.hall_of_fame) {
            FavouriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun BottomNav(modifier: Modifier = Modifier){
    BottomNavigation(modifier, backgroundColor = Color.White) {
        BottomNavigationItem(
            icon = {
                Icon (
                    imageVector =Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            label = {
                Text(text = "Stars", color = Color.Black)
            },
            selected = true,
            onClick = {}
        )

        BottomNavigationItem(
            icon = {
                Icon (
                    imageVector =Icons.Default.AccountCircle,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            label = {
                Text(text = "Profile", color = Color.Black)
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun FullApp() {
    BasicLayoutTheme() {
        Scaffold(
            bottomBar = { BottomNav() }
        ) { padding ->
            FullView(Modifier.padding(padding).background(Color.White).fillMaxSize())
        }
    }
}


//previews of the composable functions
@Preview
@Composable
fun DefaultPreview() {
    BasicLayoutTheme {
        SearchBar()
    }
}

@Preview
@Composable
fun DefaultPreview1() {
    BasicLayoutTheme {
        AlignYourBodyElement(
            text = R.string.heading,
            drawable = R.drawable.image1,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun DefaultPreview2() {
    BasicLayoutTheme {
        AlignYourBodyElement(
            text = R.string.heading,
            drawable = R.drawable.image1,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
fun DefaultPreview3() {
    BasicLayoutTheme {
        FavouriteCardCollection(
            text= R.string.kobe,
            drawable = R.drawable.kobe
        )
    }
}


@Preview
@Composable
fun DefaultPreview4() {
    BasicLayoutTheme {
        AlignYourBodyRow()
    }
}

@Preview
@Composable
fun DefaultPreview5() {
    BasicLayoutTheme {
        FavouriteCollectionsGrid()
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview() {
    BasicLayoutTheme() {
        HomeSection(title = R.string.hall_of_fame) {
            AlignYourBodyRow()
        }
    }
}

@Preview
@Composable
fun DefaultPreview7() {
    BasicLayoutTheme {
        FullView()
    }
}
@Preview
@Composable
fun DefaultPreview8() {
    BasicLayoutTheme {
        BottomNav()
    }
}


private val alignYourBodyData = listOf(
    R.drawable.kobe to R.string.kobe,
    R.drawable.curry to R.string.curry,
    R.drawable.jordan to R.string.jordan,
    R.drawable.shaq to R.string.shaq,
    R.drawable.trae to R.string.trae,
    R.drawable.lebron to R.string.lebron
).map{DrawableStringPair(it.first, it.second)}

private val favoriteCollectionsData = listOf(
    R.drawable.kobe to R.string.kobe,
    R.drawable.curry to R.string.curry,
    R.drawable.jordan to R.string.jordan,
    R.drawable.shaq to R.string.shaq,
    R.drawable.trae to R.string.trae,
    R.drawable.lebron to R.string.lebron
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)