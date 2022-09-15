package com.capzim.news;

import com.capzim.news.entity.Publication;
import com.capzim.news.service.PublicationServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@EnableAsync
public class NewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}

	@Bean
	CommandLineRunner run(PublicationServiceImpl publicationService) {
		return args -> {
			List<Publication> publications = publicationService.findAll();

			if (publications.isEmpty()){
				Publication publication;

				publication = new Publication();
				publication.setName("NewsDay");
				publication.setDescription("NewsDay is a Harare-based Zimbabwean independent daily newspaper published since 2010. It began publishing on 4 June 2010 and is based in Harare. It carries the slogan Everyday News for Everyday People on its logo.");
				publication.setHomeUrl("https://www.newsday.co.zw/category/business/");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("Time");
				publication.setDescription("Time is an American news magazine and news website published and based in New York City. For nearly a century, it was published weekly, but starting in March 2020 it transitioned to every other week.");
				publication.setHomeUrl("https://time.com/section/business/");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("Bloomberg");
				publication.setDescription("Bloomberg L.P. is a privately held financial, software, data, and media company headquartered in Midtown Manhattan, New York City.");
				publication.setHomeUrl("https://www.bloomberg.com/markets");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("The Chronicle");
				publication.setDescription("The Chronicle is a popular daily newspaper in Zimbabwe. It is published in Bulawayo and mostly reports on news in the Matebeleland region in the southern part of the country. It is state-owned and therefore usually only publishes news that supports the government and its policies.");
				publication.setHomeUrl("https://www.chronicle.co.zw/category/s6-demo-section/c41-business/");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("The Times");
				publication.setDescription("The Times is a British daily national newspaper based in London. It began in 1785 under the title The Daily Universal Register, adopting its current name on 1 January 1788.");
				publication.setHomeUrl("https://www.thetimes.co.uk/business");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("The Financial Gazette");
				publication.setDescription("The Financial Gazette is a weekly English language newspaper published in Zimbabwe. The paper, established in 1969, focuses on business, finance, and politics throughout Southern Africa. Headquartered in Harare, the paper also maintains a bureau in Bulawayo.");
				publication.setHomeUrl("https://fingaz.co.zw/category/c77-companies-a-markets/");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("The Economist");
				publication.setDescription("The Economist is a British weekly newspaper printed in demitab format and published digitally that focuses on current affairs, international business, politics, technology, and culture.");
				publication.setHomeUrl("https://www.economist.com/");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("The Sunday Mail");
				publication.setDescription("Sunday paper in Harare, Zimbabwe, sister paper to The Herald");
				publication.setHomeUrl("https://www.sundaymail.co.zw/category/business");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("Business Weekly");
				publication.setDescription("Business Weekly is a Zimbabwean online publication that focuses on business and economic current affairs. The paper was founded in 2017 by Zimpapers.");
				publication.setHomeUrl("https://www.businessweekly.co.zw/category/business-news/company-news/");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("The Zimbabwe Independent");
				publication.setDescription("Zimbabwe Independent is a private weekly newspaper published from Harare, Zimbabwe, by Alpha Media Holdings. The company also publishes The Standard and NewsDay");
				publication.setHomeUrl("https://www.theindependent.co.zw/zimbabwe-business-stories/");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("The Herald NewsPaper");
				publication.setDescription("The Herald is a state-owned daily newspaper published in Harare, the capital of Zimbabwe.");
				publication.setHomeUrl("https://www.herald.co.zw/category/articles/business/");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("Sky News");
				publication.setDescription("Sky News is a British English language free-to-air television news channel and organisation. Sky News is distributed via a radio news service, and through online channels. It is owned by Sky Group, a division of Comcast. John Ryley is the head of Sky News, a role he has held since June 2006.");
				publication.setHomeUrl("https://news.sky.com/business");
				publicationService.savePublication(publication);

				publication = new Publication();
				publication.setName("The New York Times");
				publication.setDescription("The New York Times is an American daily newspaper based in New York City with a worldwide readership. It was founded in 1851 by Henry Jarvis Raymond and George Jones, and was initially published by Raymond, Jones & Company.");
				publication.setHomeUrl("https://www.nytimes.com/international/section/business");
				publicationService.savePublication(publication);

			}
		};
	}

}
