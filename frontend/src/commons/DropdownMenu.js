import './menu.css';
import React, { useEffect, useRef, useState } from 'react';
import styled from 'styled-components/macro';
import { CgChevronRight, CgChevronLeft } from 'react-icons/cg';
import { GiSoccerBall } from 'react-icons/gi';
import { CSSTransition } from 'react-transition-group';
import SoccerTeamList from '../lists/SoccerTeamList';

export default function DropdownMenu() {
  const [activeMenu, setActiveMenu] = useState('main');
  const [menuHeight, setMenuHeight] = useState(null);
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
            leftIcon={<GiSoccerBall />}
            rightIcon={<CgChevronRight />}
            goToMenu="Bundesliga"
          >
            Bundesliga
          </DropdownItem>
          <DropdownItem
            leftIcon={<GiSoccerBall />}
            rightIcon={<CgChevronRight />}
            goToMenu="Premier League"
          >
            Premier League
          </DropdownItem>
          <DropdownItem
            leftIcon={<GiSoccerBall />}
            rightIcon={<CgChevronRight />}
            goToMenu="Primera Division"
          >
            Primera Division
          </DropdownItem>
          <DropdownItem
            leftIcon={<GiSoccerBall />}
            rightIcon={<CgChevronRight />}
            goToMenu="Ligue 1"
          >
            Ligue 1
          </DropdownItem>
          <DropdownItem
            leftIcon={<GiSoccerBall />}
            rightIcon={<CgChevronRight />}
            goToMenu="Serie A"
          >
            Serie A
          </DropdownItem>
          <DropdownItem
            leftIcon={<GiSoccerBall />}
            rightIcon={<CgChevronRight />}
            goToMenu="Eredivisie"
          >
            Eredivisie
          </DropdownItem>
          <DropdownItem
            leftIcon={<GiSoccerBall />}
            rightIcon={<CgChevronRight />}
            goToMenu="Primeira Liga"
          >
            Primeira Liga
          </DropdownItem>
        </div>
      </CSSTransition>

      <CSSTransition
        in={activeMenu === 'Bundesliga'}
        timeout={500}
        classNames="menu-secondary"
        unmountOnExit
        onEnter={calcHeight}
      >
        <div className="menu">
          <DropdownItem leftIcon={<CgChevronLeft />} goToMenu="main">
            <h3>Bundesliga</h3>
          </DropdownItem>
          <SoccerTeamList leagueName="Bundesliga" />
        </div>
      </CSSTransition>

      <CSSTransition
        in={activeMenu === 'Premier League'}
        timeout={500}
        classNames="menu-secondary"
        unmountOnExit
        onEnter={calcHeight}
      >
        <div className="menu">
          <DropdownItem leftIcon={<CgChevronLeft />} goToMenu="main">
            <h3>Premier League</h3>
          </DropdownItem>
          <SoccerTeamList leagueName="Premier League" />
        </div>
      </CSSTransition>

      <CSSTransition
        in={activeMenu === 'Primera Division'}
        timeout={500}
        classNames="menu-secondary"
        unmountOnExit
        onEnter={calcHeight}
      >
        <div className="menu">
          <DropdownItem leftIcon={<CgChevronLeft />} goToMenu="main">
            <h3>Primera Division</h3>
          </DropdownItem>
          <SoccerTeamList leagueName="Primera Division" />
        </div>
      </CSSTransition>

      <CSSTransition
        in={activeMenu === 'Ligue 1'}
        timeout={500}
        classNames="menu-secondary"
        unmountOnExit
        onEnter={calcHeight}
      >
        <div className="menu">
          <DropdownItem leftIcon={<CgChevronLeft />} goToMenu="main">
            <h3>Ligue 1</h3>
          </DropdownItem>
          <SoccerTeamList leagueName="Ligue 1" />
        </div>
      </CSSTransition>

      <CSSTransition
        in={activeMenu === 'Serie A'}
        timeout={500}
        classNames="menu-secondary"
        unmountOnExit
        onEnter={calcHeight}
      >
        <div className="menu">
          <DropdownItem leftIcon={<CgChevronLeft />} goToMenu="main">
            <h3>Serie A</h3>
          </DropdownItem>
          <SoccerTeamList leagueName="Serie A" />
        </div>
      </CSSTransition>

      <CSSTransition
        in={activeMenu === 'Eredivisie'}
        timeout={500}
        classNames="menu-secondary"
        unmountOnExit
        onEnter={calcHeight}
      >
        <div className="menu">
          <DropdownItem leftIcon={<CgChevronLeft />} goToMenu="main">
            <h3>Eredivisie</h3>
          </DropdownItem>
          <SoccerTeamList leagueName="Eredivisie" />
        </div>
      </CSSTransition>

      <CSSTransition
        in={activeMenu === 'Primeira Liga'}
        timeout={500}
        classNames="menu-secondary"
        unmountOnExit
        onEnter={calcHeight}
      >
        <div className="menu">
          <DropdownItem leftIcon={<CgChevronLeft />} goToMenu="main">
            <h3>Primeira Liga</h3>
          </DropdownItem>
          <SoccerTeamList leagueName="Primeira Liga" />
        </div>
      </CSSTransition>
    </DropdownMenuStyled>
  );
}

const DropdownItemStyled = styled.div`
  height: 1.2em;
  display: flex;
  align-items: center;
  border-radius: var(--border-radius);
  transition: background var(--speed);
  padding: 0.5em;
  color: var(--blue-main);
  font-size: var(--size-m);
  text-decoration: none;

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
  top: 50px;
  width: 200px;
  transform: translateX(-84%);
  background-color: ghostwhite;
  border: var(--blue-border);
  border-radius: var(--border-radius);
  padding: 0.4rem;
  overflow: hidden;
  transition: height var(--speed) ease;
`;
