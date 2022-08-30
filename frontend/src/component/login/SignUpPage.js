import React, { useState } from "react";
import { HomeButton, LoginButton, StyledInput, SignUpPageBox } from "./Box";
import { useNavigate } from "react-router-dom";
import signUpApi from "./../../api/login/SignUpApi";

const SignUpPage = (props) => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [checkPassword, setCheckPassword] = useState("");
  const [nickName, setNickName] = useState("");
  const [age, setAge] = useState("");

  const [sex, setSex] = useState("");
  const [userName, setUserName] = useState("");

  const checkOnlyOne = (checkThis) => {
    const checkboxes = document.getElementsByName("sex");
    for (let i = 0; i < checkboxes.length; i++) {
      if (checkboxes[i] !== checkThis) {
        checkboxes[i].checked = false;
      } else {
        setSex(checkThis.value);
      }
    }
  };

  return (
    <>
      <HomeButton
        onClick={() => {
          navigate("/");
        }}
      />
      <SignUpPageBox>
        <StyledInput
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder={"이메일"}
        />
        <StyledInput
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder={"비밀번호"}
        />
        <StyledInput
          type="password"
          value={checkPassword}
          onChange={(e) => setCheckPassword(e.target.value)}
          placeholder={"비밀번호 재확인"}
        />
        <StyledInput
          value={nickName}
          onChange={(e) => setNickName(e.target.value)}
          placeholder={"닉네임"}
        />
        <StyledInput
          value={userName}
          onChange={(e) => setUserName(e.target.value)}
          placeholder={"이름"}
        />
        <StyledInput
          value={age}
          onChange={(e) => setAge(e.target.value)}
          placeholder={"나이"}
        />
        <StyledInput
          type="text"
          id="pInput"
          readOnly={true}
          placeholder={"도시"}
          onClick={() => {
            window.name = "parentForm";
            window.open(
              "/signup/city",
              "childForm",
              "top=10, left=10, width=650, height=600, status=no, menubar=no, toolbar=no, resizable=no"
            );
          }}
        />
        <div
          style={{
            display: "inline-block",
            width: "60px",
            fontSize: "20px",
            marginLeft: "-40px",
          }}
        >
          성별:
        </div>
        <input
          type="checkbox"
          name="sex"
          value={"남성"}
          onChange={(e) => checkOnlyOne(e.target)}
          style={{ display: "inline-block", width: "100px", height: "20px" }}
        />{" "}
        <div
          style={{
            display: "inline-block",
            width: "40px",
            fontSize: "20px",
            marginLeft: "-40px",
          }}
        >
          남
        </div>
        <input
          type="checkbox"
          name="sex"
          value={"여성"}
          onChange={(e) => checkOnlyOne(e.target)}
          style={{ display: "inline-block", width: "100px", height: "20px" }}
        />{" "}
        <div
          style={{
            display: "inline-block",
            width: "40px",
            fontSize: "20px",
            marginLeft: "-40px",
          }}
        >
          여
        </div>
        <LoginButton
          onClick={() => {
            const isValidEmail = email.includes("@") && email.includes(".");
            if (!isValidEmail) alert("이메일 형식을 맞춰주세요!!");
            else if (
              email.length &&
              password.length &&
              checkPassword.length &&
              nickName.length &&
              age.length &&
              sex.length &&
              document.getElementById("pInput").value &&
              userName.length
            ) {
              if (password === checkPassword) {
                signUpApi(
                  email,
                  password,
                  nickName,
                  age,
                  sex,
                  document.getElementById("pInput").value,
                  userName,
                  navigate
                );
              } else alert("비밀번호가 일치하지 않습니다");
            } else {
              alert("빈칸을 다 채워주세요");
            }
          }}
        >
          가입하기
        </LoginButton>
      </SignUpPageBox>
    </>
  );
};

export default SignUpPage;
