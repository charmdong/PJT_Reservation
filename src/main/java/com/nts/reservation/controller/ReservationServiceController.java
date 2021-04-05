package com.nts.reservation.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.service.ReservationService;

/**
 * 예약 서비스 Controller
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 7. 29.
 */
@Controller
@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
public class ReservationServiceController {
	@Autowired
	ReservationService reservationService;

	/**
	 * @return "mainpage"
	 */
	@GetMapping(path = { "/", "/mainpage" })
	public String mainPage() {
		return "mainpage";
	}

	/**
	 * @param displayInfoId
	 * @param model
	 * @return "detail"
	 */
	@GetMapping(path = "/detail")
	public String detail(@RequestParam(name = "displayInfoId", required = true) int displayInfoId, ModelMap model) {
		model.addAttribute("displayInfoId", displayInfoId);

		return "detail";
	}

	/**
	 * @param displayInfoId
	 * @param model
	 * @return "review"
	 */
	@GetMapping(path = "/review")
	public String review(@RequestParam(name = "displayInfoId", required = true) int displayInfoId, ModelMap model) {
		model.addAttribute("displayInfoId", displayInfoId);

		return "review";
	}

	/**
	 * @param displayInfoId
	 * @param model
	 * @return "reserve"
	 */
	@GetMapping(path = "/reserve")
	public String reserve(@RequestParam(name = "displayInfoId", required = true) int displayInfoId, ModelMap model) {
		model.addAttribute("displayInfoId", displayInfoId);
		model.addAttribute("randReservationDate", reservationService.getRandReservationDate());

		return "reserve";
	}

	/**
	 * @param reservationEmail
	 * @param response
	 * @param model
	 * @return "myreservation"
	 */
	@PostMapping(path = "/myreservation")
	public String myReservation(@RequestParam(name = "reservationEmail", required = false) String reservationEmail,
			HttpServletResponse response, ModelMap model) {
		if (reservationEmail != null) { // 첫 로그인의 경우
			Cookie cookie = new Cookie("reservationEmail", reservationEmail);
			cookie.setMaxAge(60 * 60 * 24 * 365);
			cookie.setPath("/");

			response.addCookie(cookie);
		}

		return "myreservation";
	}

	/**
	 * @return "bookinglogin"
	 */
	@GetMapping(path = "/bookinglogin")
	public String bookingLogin() {
		return "bookinglogin";
	}
	
	/**
	 * @param reservationInfoId
	 * @param productId
	 * @param model
	 * @return "reviewWrite"
	 */
	@GetMapping(path = "/reviewWrite")
	public String reviewWrite(@RequestParam(name = "reservationInfoId", required = true) int reservationInfoId, 
			@RequestParam(name = "productId", required = true) int productId, ModelMap model) {
		model.addAttribute("reservationInfoId", reservationInfoId);
		model.addAttribute("productId", productId);
		
		return "reviewWrite";
	}
}
