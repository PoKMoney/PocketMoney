import React from "react";
import styled from "styled-components";
import WishList from "./WishList";
import WishListOpenButton from "./WishListOpenButton";

const StyledElement = styled.div`
  margin: 10px 0;
  font-size: 30px;
  padding: 4px 10px;
  cursor: pointer;
  width: 230px;
  color: rgb(24, 191, 230);
`;
function WishBoards(props) {
  return (
    <>
      <StyledElement
        onClick={() => props.setIsOpened(!props.isOpened)}
        style={{ marginTop: "19px" }}
      >
        내 위시리스트
        <WishListOpenButton
          isOpened={props.isOpened}
          setIsOpened={props.setIsOpened}
        />
      </StyledElement>
      <WishList isOpened={props.isOpened} wishBoards={props.wishBoards} />
    </>
  );
}

export default WishBoards;
