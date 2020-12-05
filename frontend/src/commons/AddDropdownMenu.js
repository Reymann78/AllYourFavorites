import './menu.css';
import React, { useContext, useEffect, useRef, useState } from 'react';
import styled from 'styled-components/macro';
import { CgChevronRight, CgChevronLeft } from 'react-icons/cg';
import { GiSoccerBall } from 'react-icons/gi';
import { BsFillCaretUpFill } from 'react-icons/bs';
import { CSSTransition } from 'react-transition-group';
import SoccerTeamList from '../component/SoccerTeamList';
import OpenMenuContext from '../contexts/OpenMenuContext';

export default function AddDropdownMenu() {
  const [activeMenu, setActiveMenu] = useState('main');
  const [menuHeight, setMenuHeight] = useState(null);
  const { open, setOpen } = useContext(OpenMenuContext);
  const dropdownRef = useRef(null);

  useEffect(() => {
    setMenuHeight(dropdownRef.current?.firstChild.offsetHeight);
  }, []);

  function calcHeight(el) {
    const height = el.offsetHeight;
    setMenuHeight(height);
  }

  function DropdownItem(props) {
    return (
      <DropdownItemStyled
        onClick={() =>
          (props.onClick && props.onClick()) ||
          (props.goToMenu && setActiveMenu(props.goToMenu))
        }
      >
        <LeftIconStyled>{props.leftIcon}</LeftIconStyled>
        {props.children}
        <RightIconStyled>{props.rightIcon}</RightIconStyled>
      </DropdownItemStyled>
    );
  }

  return (
    <DropdownMenuStyled style={{ height: menuHeight }} ref={dropdownRef}>
      <CSSTransition
        in={activeMenu === 'main'}
        timeout={500}
        classNames="menu-primary"
        unmountOnExit
        onEnter={calcHeight}
      >
        <div className="menu">
          <DropdownItem
            leftIcon={<BsFillCaretUpFill />}
            onClick={() => setOpen(!open)}
          >
            <h3>Exit</h3>
          </DropdownItem>
          <DropdownItem
            leftIcon={<GiSoccerBall />}
            rightIcon={<CgChevronRight />}
            goToMenu="bundesliga"
          >
            Bundesliga
          </DropdownItem>
          <DropdownItem
            leftIcon={<GiSoccerBall />}
            rightIcon={<CgChevronRight />}
            goToMenu="premierLeague"
          >
            Premier League
          </DropdownItem>
        </div>
      </CSSTransition>

      <CSSTransition
        in={activeMenu === 'bundesliga'}
        timeout={500}
        classNames="menu-secondary"
        unmountOnExit
        onEnter={calcHeight}
      >
        <div className="menu">
          <DropdownItem leftIcon={<CgChevronLeft />} goToMenu="main">
            <h3>Bundesliga</h3>
          </DropdownItem>
          <SoccerTeamList />
        </div>
      </CSSTransition>

      <CSSTransition
        in={activeMenu === 'premierLeague'}
        timeout={500}
        classNames="menu-secondary"
        unmountOnExit
        onEnter={calcHeight}
      >
        <div className="menu">
          <DropdownItem leftIcon={<CgChevronLeft />} goToMenu="main">
            <h3>Premier League</h3>
          </DropdownItem>
          <SoccerTeamList />
        </div>
      </CSSTransition>
    </DropdownMenuStyled>
  );
}

const DropdownItemStyled = styled.div`
  height: 50px;
  display: flex;
  align-items: center;
  border-radius: var(--border-radius);
  transition: background var(--speed);
  padding: 0.2rem;
  color: var(--blue-main);
  font-size: var(--size-l);
  text-decoration: none;
  margin-right: 2px;

  &:hover {
    background-color: lightgrey;
  }
`;

const LeftIconStyled = styled.span`
  --button-size: calc(var(--nav-size) * 0.8);
  width: var(--button-size);
  height: var(--button-size);
  color: var(--blue-main);
  border-radius: var(--border-radius);
  display: flex;
  align-items: center;
  justify-content: center;
`;

const RightIconStyled = styled.span`
  margin-left: auto;
`;

const DropdownMenuStyled = styled.span`
  position: absolute;
  top: 70px;
  width: 240px;
  transform: translateX(-45%);
  background-color: ghostwhite;
  border: var(--blue-border);
  border-radius: var(--border-radius);
  padding: 0.5rem;
  overflow: hidden;
  transition: height var(--spees) ease;
`;
