import React, { useState } from "react";
import { HomeButton, LoginButton, StyledInput, SignUpPageBox } from "./Box";
import { useNavigate } from "react-router-dom";
import signUpApi from "../../api/SignUpApi";

const SignUpPage = (props) => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [checkPassword, setCheckPassword] = useState("");
  const [nickName, setNickName] = useState("");
  const [age, setAge] = useState("");
  const [sex, setSex] = useState("");
  const [city, setCity] = useState("");
  const [userName, setUserName] = useState("");

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
          value={sex}
          onChange={(e) => setSex(e.target.value)}
          placeholder={"성별"}
        />
        <StyledInput
          value={city}
          onChange={(e) => setCity(e.target.value)}
          placeholder={"도시"}
        />
        <LoginButton
          onClick={() => {
            if (
              email.length &&
              password.length &&
              checkPassword.length &&
              nickName.length &&
              age.length &&
              sex.length &&
              city.length &&
              userName.length
            ) {
              if (password === checkPassword) {
                signUpApi(
                  email,
                  password,
                  nickName,
                  age,
                  sex,
                  city,
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