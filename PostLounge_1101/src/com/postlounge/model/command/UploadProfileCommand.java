package com.postlounge.model.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.postlounge.model.dao.MemberDAO;
import com.postlounge.model.vo.MemberVO;

public class UploadProfileCommand implements Command {
    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = "C:/MyStudy/60_JSP_Servlet/PostLounge_1030_SMK/imageupload"; // 업로드 경로 설정

        MultipartRequest mr = new MultipartRequest(
            request,
            uploadPath,
            10 * 1024 * 1024,
            "UTF-8",
            new DefaultFileRenamePolicy()
        );

        // 업로드된 파일명 가져오기
        String uploadedFilename = mr.getFilesystemName("imageFile");
        String originalFilename = mr.getOriginalFileName("imageFile");
        System.out.println("uploadedFilename : " + uploadedFilename);
        System.out.println("originalFilename : " + originalFilename);
        // 파일 정보를 데이터베이스에 저장
        MemberVO member = (MemberVO) request.getSession().getAttribute("loginUser");
        System.out.println("member : " + member);
        member.setMemberOriName(originalFilename);
        member.setMemberFileName(uploadedFilename);

        // 데이터베이스 업데이트
        MemberDAO.updateProfile(member);

        // 데이터베이스 저장 후 프로필 정보 페이지로 이동
        return "profileInfo.jsp";
    }
}
