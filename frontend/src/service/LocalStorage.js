const ACCESS_TOKEN = 'ACCESS_TOKEN';
const USER_DATA = 'USER_DATA';

export const loadTokenFromLocalStorage = () =>
  localStorage.getItem(ACCESS_TOKEN);

export const saveTokenToLocalStorage = (token) =>
  localStorage.setItem(ACCESS_TOKEN, token);

export const loadUserDataFromLocalStorage = () => {
  const userDataString = localStorage.getItem(USER_DATA);

  try {
    return JSON.parse(userDataString);
  } catch (e) {
    console.error(e);
  }
};

export const saveUserDataToLocalStorage = (userData) => {
  localStorage.setItem(USER_DATA, JSON.stringify(userData));
};

export const deleteTokenFromLocalStorage = () => {
  localStorage.removeItem(ACCESS_TOKEN);
};

export const deleteUserDataFromLocalStorage = () =>
  localStorage.removeItem(USER_DATA);
