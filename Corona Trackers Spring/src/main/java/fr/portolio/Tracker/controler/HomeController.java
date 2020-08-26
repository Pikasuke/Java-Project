package fr.portolio.Tracker.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.portolio.Tracker.models.LocationStats;
import fr.portolio.Tracker.services.CoronaDataServices;

@Controller
public class HomeController {
	
	@Autowired
	CoronaDataServices coronaServices;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaServices.getAllStats();
		
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLastestTotalCases()).sum();
		
		int totalNewestCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
		
		model.addAttribute("locationStat", allStats);
		model.addAttribute("totalReportedCase", totalReportedCases);
		model.addAttribute("totalNewestCases", totalNewestCases);
		return "home";
	}
}
