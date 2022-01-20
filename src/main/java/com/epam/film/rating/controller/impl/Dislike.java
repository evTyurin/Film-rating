package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.review.ReviewApproval;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Dislike implements Command {

    public final String id = "id";
    public final String userID = "userId";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int reviewId = Integer.parseInt(request.getParameter(id));
        int dislikesAmount;

        try {
            HttpSession session = request.getSession();
            int userId = (Integer)session.getAttribute(userID);

            ReviewDAOImpl reviewDAO = new ReviewDAOImpl(); //TODO ServiceFactory
            dislikesAmount = reviewDAO.getDislikesAmountById(reviewId);
            ReviewApproval reviewApproval = reviewDAO.getReviewApprovalById(userId, reviewId);
            String dislikes = null;
            if(reviewApproval != null) {


                if(reviewApproval.isDisliked()) {
                    reviewDAO.updateReviewApprovalDislike(false, userId, reviewId);

                    dislikesAmount--;
                    reviewDAO.updateDislikesAmountById(dislikesAmount, reviewId);
                    dislikes = Integer.toString(dislikesAmount);

                } else if(reviewApproval.isLiked() ) {
                    reviewDAO.updateReviewApprovalDislike(true, userId, reviewId);

                    dislikesAmount++;
                    reviewDAO.updateDislikesAmountById(dislikesAmount, reviewId);
                    dislikes = Integer.toString(dislikesAmount);
                } else {
                    reviewDAO.updateReviewApprovalDislike(true, userId, reviewId);

                    dislikesAmount++;
                    reviewDAO.updateDislikesAmountById(dislikesAmount, reviewId);
                    dislikes = Integer.toString(dislikesAmount);
                }
            } else {
                reviewDAO.addReviewApproval(userId, reviewId, false, true);

                dislikesAmount++;
                reviewDAO.updateDislikesAmountById(dislikesAmount, reviewId);
                dislikes = Integer.toString(dislikesAmount);
            }

            response.setContentType("text/plain");
            response.getWriter().write(dislikes);

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
