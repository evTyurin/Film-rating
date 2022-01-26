package com.epam.film.rating.controller.impl;

import com.epam.film.rating.controller.Command;
import com.epam.film.rating.dao.impl.ReviewDAOImpl;
import com.epam.film.rating.entity.review.ReviewApproval;
import com.epam.film.rating.service.Service;
import com.epam.film.rating.service.ServiceFactory;
import com.epam.film.rating.service.exception.ServiceException;

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

            ServiceFactory instance = ServiceFactory.getInstance();
            Service service = instance.getService();

            dislikesAmount = service.getDislikesAmountById(reviewId);
            ReviewApproval reviewApproval = service.getReviewApprovalById(userId, reviewId);
            String dislikes = null;
            if(reviewApproval != null) {

                if(reviewApproval.isDisliked()) {
                    service.updateReviewApprovalDislike(false, userId, reviewId);

                    dislikesAmount--;
                    service.updateDislikesAmountById(dislikesAmount, reviewId);
                    dislikes = Integer.toString(dislikesAmount);

                } else if(reviewApproval.isLiked() ) {
                    service.updateReviewApprovalDislike(true, userId, reviewId);

                    dislikesAmount++;
                    service.updateDislikesAmountById(dislikesAmount, reviewId);
                    dislikes = Integer.toString(dislikesAmount);
                } else {
                    service.updateReviewApprovalDislike(true, userId, reviewId);

                    dislikesAmount++;
                    service.updateDislikesAmountById(dislikesAmount, reviewId);
                    dislikes = Integer.toString(dislikesAmount);
                }
            } else {
                service.addReviewApproval(userId, reviewId, false, true);

                dislikesAmount++;
                service.updateDislikesAmountById(dislikesAmount, reviewId);
                dislikes = Integer.toString(dislikesAmount);
            }

            response.setContentType("text/plain");
            response.getWriter().write(dislikes);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
