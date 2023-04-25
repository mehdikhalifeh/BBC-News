package com.mehdi.bbcnews.data.remote.connection

class FakeResponseBody {
    fun returnResponse():String{
        return "{\n" +
                "    \"status\": \"ok\",\n" +
                "    \"totalResults\": 10,\n" +
                "    \"articles\": [\n" +
                "        {\n" +
                "            \"source\": {\n" +
                "                \"id\": \"bbc-news\",\n" +
                "                \"name\": \"BBC News\"\n" +
                "            },\n" +
                "            \"author\": \"BBC News\",\n" +
                "            \"title\": \"Dame Edna's memorable moments in 60 seconds\",\n" +
                "            \"description\": \"A look back at some laughs from the comedian, Barry Humphries, best known for character Dame Edna Everage.\",\n" +
                "            \"url\": \"http://www.bbc.co.uk/news/entertainment-arts-65358301\",\n" +
                "            \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/4EE2/production/_129449102_b5f6c0752bee38fc657f098fb3387e303ad56ccb0_290_2364_13291000x563.jpg\",\n" +
                "            \"publishedAt\": \"2023-04-22T15:37:19.3827616Z\",\n" +
                "            \"content\": \"A look back at some of the funniest moments from Dame Edna Everage.\\r\\nShe was one of comedian Barry Humphries' most known characters. Humphries has died at the age of 89.\\r\\nRead more about the star's l… [+8 chars]\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"source\": {\n" +
                "                \"id\": \"bbc-news\",\n" +
                "                \"name\": \"BBC News\"\n" +
                "            },\n" +
                "            \"author\": \"BBC News\",\n" +
                "            \"title\": \"Russia's Belgorod sees mass evacuations over undetonated bomb\",\n" +
                "            \"description\": \"An undetonated bomb was found in Belgorod, where a jet accidentally dropped another bomb days earlier.\",\n" +
                "            \"url\": \"http://www.bbc.co.uk/news/world-europe-65358070\",\n" +
                "            \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/437A/production/_129447271_gettyimages-1252028554.jpg\",\n" +
                "            \"publishedAt\": \"2023-04-22T14:37:14.7569359Z\",\n" +
                "            \"content\": \"More than 3,000 people have been evacuated from their homes in the Russian city of Belgorod after an undetonated explosive was found.\\r\\nIt comes two days after Russia accidentally dropped a bomb on th… [+1573 chars]\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"source\": {\n" +
                "                \"id\": \"bbc-news\",\n" +
                "                \"name\": \"BBC News\"\n" +
                "            },\n" +
                "            \"author\": \"BBC News\",\n" +
                "            \"title\": \"Barry Humphries: Dame Edna Everage comedian dies at 89\",\n" +
                "            \"description\": \"The Australian was known for comic creations such as Dame Edna, Sir Les Patterson and Sandy Stone.\",\n" +
                "            \"url\": \"http://www.bbc.co.uk/news/entertainment-arts-65328507\",\n" +
                "            \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/2F6D/production/_129414121_gettyimages-526509642.jpg\",\n" +
                "            \"publishedAt\": \"2023-04-22T11:07:15.5178309Z\",\n" +
                "            \"content\": \"Australian entertainer Barry Humphries, best known for his comic character Dame Edna Everage, has died aged 89.\\r\\nThe star had been in hospital in Sydney after suffering complications following hip su… [+2368 chars]\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"source\": {\n" +
                "                \"id\": \"bbc-news\",\n" +
                "                \"name\": \"BBC News\"\n" +
                "            },\n" +
                "            \"author\": \"BBC News\",\n" +
                "            \"title\": \"Sudan fighting: Khartoum violence mapped as civilians flee city\",\n" +
                "            \"description\": \"The BBC has verified videos of fighting and spoken to fleeing residents to map the violence in Sudan's capital.\",\n" +
                "            \"url\": \"https://www.bbc.co.uk/news/world-africa-65338181\",\n" +
                "            \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/44FF/production/_129436671_gettyimages-1251992156.jpg\",\n" +
                "            \"publishedAt\": \"2023-04-22T10:22:19.3807555Z\",\n" +
                "            \"content\": \"Life in Sudan's capital Khartoum has been turned on its head. \\r\\nOnce a vibrant metropolis, residents are now living in a war zone. Sudan's army and the paramilitary Rapid Support Forces (RSF) are loc… [+8962 chars]\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"source\": {\n" +
                "                \"id\": \"bbc-news\",\n" +
                "                \"name\": \"BBC News\"\n" +
                "            },\n" +
                "            \"author\": \"BBC News\",\n" +
                "            \"title\": \"Foreign nationals to be evacuated from Sudan - army\",\n" +
                "            \"description\": \"Nationals from the US, UK, France and China are to be evacuated as fighting in Sudan enters second week.\",\n" +
                "            \"url\": \"http://www.bbc.co.uk/news/uk-65358069\",\n" +
                "            \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/83B3/production/_115651733_breaking-large-promo-nc.png\",\n" +
                "            \"publishedAt\": \"2023-04-22T10:22:17.7564947Z\",\n" +
                "            \"content\": \"Diplomats and nationals from the US, UK, France and China are to be evacuated from Sudan as fighting there continues, a statement from the Sudanese army says.\\r\\nArmy chief Fattah al-Burhan agreed to f… [+575 chars]\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"source\": {\n" +
                "                \"id\": \"bbc-news\",\n" +
                "                \"name\": \"BBC News\"\n" +
                "            },\n" +
                "            \"author\": \"BBC News\",\n" +
                "            \"title\": \"Waterspout crashes onto beach full of people in Miami\",\n" +
                "            \"description\": \"Strong winds send a man flying, luckily to no harm, while sun chairs scatter along Miami beach front.\",\n" +
                "            \"url\": \"http://www.bbc.co.uk/news/world-us-canada-65358298\",\n" +
                "            \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/329A/production/_129445921_p0fj5dwt.jpg\",\n" +
                "            \"publishedAt\": \"2023-04-22T08:37:13.8511907Z\",\n" +
                "            \"content\": \"Video from a CBS news helicopter shows the moment a waterspout crashed onto a beach full of people.\\r\\nStrong winds sent one man flying backwards but luckily to no harm at Hollywood Beach, Miami. \\r\\nThe… [+118 chars]\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"source\": {\n" +
                "                \"id\": \"bbc-news\",\n" +
                "                \"name\": \"BBC News\"\n" +
                "            },\n" +
                "            \"author\": \"BBC News\",\n" +
                "            \"title\": \"Family of Halyna Hutchins to proceed with civil lawsuit against Baldwin\",\n" +
                "            \"description\": \"Lawyers for her parents and sister say the Rust actor \\\"cannot escape responsibility\\\" for her death.\",\n" +
                "            \"url\": \"http://www.bbc.co.uk/news/entertainment-arts-65355387\",\n" +
                "            \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/9758/production/_129444783_gettyimages-1245427091.jpg\",\n" +
                "            \"publishedAt\": \"2023-04-21T23:37:18.5554511Z\",\n" +
                "            \"content\": \"Media caption, Watch: Alec Baldwin rehearses with gun before fatal shooting\\r\\nThe family of Halyna Hutchins, the Rust cinematographer who died on set, say they will sue Alec Baldwin despite his crimin… [+2805 chars]\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"source\": {\n" +
                "                \"id\": \"bbc-news\",\n" +
                "                \"name\": \"BBC News\"\n" +
                "            },\n" +
                "            \"author\": \"BBC News\",\n" +
                "            \"title\": \"Supreme Court preserves abortion drug access\",\n" +
                "            \"description\": \"US Supreme Court preserves access to the common abortion drug mifepristone, while a legal case over restrictions continues\",\n" +
                "            \"url\": \"http://www.bbc.co.uk/news/world-us-canada-65356390\",\n" +
                "            \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/83B3/production/_115651733_breaking-large-promo-nc.png\",\n" +
                "            \"publishedAt\": \"2023-04-21T23:07:18.7530349Z\",\n" +
                "            \"content\": \"The US Supreme Court has preserved access to a commonly used abortion pill, ruling the drug can remain available while a lawsuit continues.\\r\\nIn a split decision, it also rejected restrictions on the … [+358 chars]\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"source\": {\n" +
                "                \"id\": \"bbc-news\",\n" +
                "                \"name\": \"BBC News\"\n" +
                "            },\n" +
                "            \"author\": \"BBC News\",\n" +
                "            \"title\": \"Fire breaks out on US bridge after fuel tanker explosion\",\n" +
                "            \"description\": \"One person was killed in the crash which shut down a major motorway.\",\n" +
                "            \"url\": \"http://www.bbc.co.uk/news/world-us-canada-65356003\",\n" +
                "            \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/1400E/production/_129443918_p0fj3pd9.jpg\",\n" +
                "            \"publishedAt\": \"2023-04-21T21:07:15.065622Z\",\n" +
                "            \"content\": \"Fire breaks out on US bridge after fuel tanker explosion. Video, 00:00:30Fire breaks out on US bridge after fuel tanker explosion\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"source\": {\n" +
                "                \"id\": \"bbc-news\",\n" +
                "                \"name\": \"BBC News\"\n" +
                "            },\n" +
                "            \"author\": \"BBC News\",\n" +
                "            \"title\": \"A recent gold heist in Canada may be the largest, but not the first\",\n" +
                "            \"description\": \"Millions of dollars worth of gold have been stolen from Canadian airports in the last century.\",\n" +
                "            \"url\": \"http://www.bbc.co.uk/news/world-us-canada-65350126\",\n" +
                "            \"urlToImage\": \"https://ichef.bbci.co.uk/news/1024/branded_news/10E49/production/_129439196_gettyimages-1138925464.jpg\",\n" +
                "            \"publishedAt\": \"2023-04-21T17:52:16.4487196Z\",\n" +
                "            \"content\": \"Police in Canada are investigating one of the largest gold heists in the country's history, after more than C\$20m (\$15m; £12m) of the precious metal and other valuable goods were stolen from Toronto'… [+4661 chars]\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"
    }
}