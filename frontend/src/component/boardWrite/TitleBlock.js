import React, { useState } from "react";
import styled from "styled-components";
const Outside = styled.div`
  width: 800px;
  height: 70px;
  line-height: 150px;
  border-top: 1px solid rgb(200, 200, 200);
  border-bottom: 1px solid rgb(200, 200, 200);
`;
const TitleInput = styled.input`
  display: block;
  font-size: 50px;
  width: 770px;
  background-color: #00000000;
  padding: 10px;
  height: 50px;
  border: none;
  &:focus {
    outline: none;
    border: none;
  }
`;
function TitleBlock({ title, setTitle }) {
  return (
    <Outside>
      <TitleInput
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        placeholder={"제목"}
      />
    </Outside>
  );
}

export default TitleBlock;
