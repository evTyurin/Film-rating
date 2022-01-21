package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Like implements Command {
    public final String currentURL = "/WEB-INF/jsp/filmDescription.jsp";
    public final String URL = "URL";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int reviewId = Integer.parseInt(request.getParameter("id"));

        int likeAmount;

        HttpSession session = request.getSession();

        int userId = (Integer)session.getAttribute("userId");

        try {
            ServiceFactory instance = ServiceFactory.getInstance();
            Service service = instance.getService();

            likeAmount = service.getLikesAmountById(reviewId);
            ReviewApproval reviewApproval = service.getReviewApprovalById(userId, reviewId);
            String likes = null;
            if(reviewApproval != null) {
                if(reviewApproval.isLiked()) {
                    // cancel like
                    service.updateReviewApprovalLike(false, userId, reviewId);

                    likeAmount--;
                    service.updateLikesAmountById(likeAmount, reviewId);
                    likes = Integer.toString(likeAmount);

                } else if(reviewApproval.isDisliked() ) {
                    //do like
                    service.updateReviewApprovalLike(true, userId, reviewId);

                    likeAmount++;
                    service.updateLikesAmountById(likeAmount, reviewId);
                    likes = Integer.toString(likeAmount);

                } else {
                    //do like
                    service.updateReviewApprovalLike(true, userId, reviewId);

                    likeAmount++;
                    service.updateLikesAmountById(likeAmount, reviewId);
                    likes = Integer.toString(likeAmount);
                }
            } else {
                //do instance with like
                service.addReviewApproval(userId, reviewId, true, false);


                likeAmount++;
                service.updateLikesAmountById(likeAmount, reviewId);
                likes = Integer.toString(likeAmount);
            }

            response.setContentType("text/plain");
            response.getWriter().write(likes);

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
