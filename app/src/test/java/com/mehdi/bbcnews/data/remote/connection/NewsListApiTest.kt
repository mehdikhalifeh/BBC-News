package com.mehdi.bbcnews.data.remote.connection

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.BuildConfig

class NewsListApiTest {
    private lateinit var newsListApi: NewsListApi
    private lateinit var mockWebServer: MockWebServer
    
    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        newsListApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsListApi::class.java)
    }
    
    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
    
    @Test
    fun testGetTopHeadlines() = runBlocking {
        val responseJson = FakeResponseBody().returnResponse()
        mockWebServer.enqueue(MockResponse().setBody(responseJson))
        
        val result = newsListApi.getTopHeadlines("bbc-news")
        
        val request = mockWebServer.takeRequest()
        assertThat(request.method).isEqualTo("GET")
        assertThat(request.path).isEqualTo("/top-headlines?sources=bbc-news")
        assertThat(request.getHeader("x-api-key")).isEqualTo(BuildConfig.API_KEY)
        
        assertThat(result.status).isEqualTo("ok")
        assertThat(result.totalResults).isEqualTo(10)
        assertThat(result.articles.size).isEqualTo(10)
        assertThat(result.articles[0].author).isEqualTo("BBC News")
        assertThat(result.articles[0].title).isEqualTo("Dame Edna's memorable moments in 60 seconds")
        assertThat(result.articles[0].description).isEqualTo("A look back at some laughs from the comedian, Barry Humphries, best known for character Dame Edna Everage.")
        assertThat(result.articles[0].url).isEqualTo("http://www.bbc.co.uk/news/entertainment-arts-65358301")
        assertThat(result.articles[0].urlToImage).isEqualTo("https://ichef.bbci.co.uk/news/1024/branded_news/4EE2/production/_129449102_b5f6c0752bee38fc657f098fb3387e303ad56ccb0_290_2364_13291000x563.jpg")
        assertThat(result.articles[0].publishedAt).isEqualTo("2023-04-22T15:37:19.3827616Z")
        assertThat(result.articles[0].content).isEqualTo("A look back at some of the funniest moments from Dame Edna Everage.\r\nShe was one of comedian Barry Humphries' most known characters. Humphries has died at the age of 89.\r\nRead more about the star's l… [+8 chars]")
    }
}
