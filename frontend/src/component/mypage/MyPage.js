/* eslint-disable jsx-a11y/img-redundant-alt */
import React, { useEffect, useState } from "react";
import { ACCESS_TOKEN } from "./../../constant/LocalStorage";
import styled from "styled-components";
import { useNavigate } from "react-router";
import CancelButton from "./../CancelButton";
import InfBox from "./InfBox";
import SubFuncBox from "./SubFuncBox";
import findUserDetailsApi from "./../../api/user/findUserDetailsApi";
import WishBoards from "./wishboards/WishBoards";
import findMyWishBoardApi from "./../../api/board/FindMyWishBoardsApi";

const Outside = styled.div`
  width: 1050px;
  height: 400px;
  margin: 10px auto;
  border: 1px solid rgb(200, 200, 200);
  overflow: auto;
`;
const MyInf = styled.div`
  width: 1050px;
  height: 370px;
`;
const BaseImg = styled.div`
  display: inline-block;
  width: 300px;
  height: 300px;
`;

function Mypage() {
  if (!sessionStorage.getItem(ACCESS_TOKEN)) {
    alert("로그인이 필요한 서비스입니다!!!");
    window.location.href = "/login";
  }
  const navigate = useNavigate();
  const accesstoken = sessionStorage.getItem(ACCESS_TOKEN);
  const [userInf, setUserInf] = useState();
  const [isBoardsOpened, setIsBoardsOpened] = useState(false);
  const [wishBoards, setWishBoards] = useState([]);
  useEffect(() => {
    findUserDetailsApi(sessionStorage.getItem(ACCESS_TOKEN)).then((resp) => {
      setUserInf(resp);
    });
    findMyWishBoardApi(accesstoken).then((resp) => {
      setWishBoards(resp.dtoList);
    });
  }, []);
  return (
    <>
      <Outside>
        <MyInf>
          <CancelButton navigate={navigate} />
          <BaseImg>
            <img
              src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"
              alt="my image"
              style={{
                marginLeft: "20px",
                width: "300px",
                height: "300px",
                borderRadius: "1000px",
              }}
            />
          </BaseImg>
          <InfBox userInf={userInf} />
          <SubFuncBox navigate={navigate} userInf={userInf} />
        </MyInf>
      </Outside>
      <Outside>
        <WishBoards
          wishBoards={wishBoards}
          isOpened={isBoardsOpened}
          setIsOpened={setIsBoardsOpened}
        />
      </Outside>
    </>
  );
}

export default Mypage;
