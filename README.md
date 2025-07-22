# ❤️ Cou Bondhu - Blood Donation App for Cumilla University

**Cou Bondhu (বন্ধু)** is a user-friendly blood donation app designed for the students of **Cumilla University**. It helps users find and connect with blood donors based on blood group, stores user profiles in the cloud using **Firebase Firestore**, and tracks blood donation status with a clean UI built in **Jetpack Compose**.

> ✅ Built with **Kotlin**, **Jetpack Compose**, **Firebase Firestore**, and **MVVM architecture**

---

## 🧠 Features

- 🔍 **Search Donors by Blood Group**
- 👤 **User Profile with Department, Session, and Contact**
- ☁️ **Cloud Storage with Firebase Firestore**
- 💉 **Track Monthly Donation Status**
- 🔐 **Login Session Stored Using DataStore**
- 📱 **Modern UI using Jetpack Compose**

---

## 📸 Screenshots

[Bondhu poster](https://github.com/user-attachments/assets/fc96df61-fd14-491b-aac0-a31d65fbd419)
![Bondhu log in](https://github.com/user-attachments/assets/009d7bf6-674e-4c45-8747-c99d12960a01)
![Bondhu home page](https://github.com/user-attachments/assets/65a10105-a87c-466c-acf7-aac8556be955)
![bondhu search](https://github.com/user-attachments/assets/93655edb-4f4e-4dd4-a600-34fe671c3f6d)

---

## 📲 Download APK

Scan the QR code or click here *(https://drive.google.com/file/d/1QiZx7zIFzW4YpuwCiANzbXRr5w68cGSj/view?usp=drive_link)* to download the app.

---

## 🛠 Tech Stack

| Layer             | Tech Used                  |
|------------------|----------------------------|
| Language          | Kotlin                     |
| UI Framework      | Jetpack Compose            |
| Architecture      | MVVM                       |
| State Management  | ViewModel, State           |
| Cloud Database    | Firebase Firestore         |
| Local Session     | DataStore (login session)  |
| Navigation        | Jetpack Navigation         |
| IDE               | Android Studio             |

---

## 📂 Project Structure

CouBondhu/
├── data/ # Firebase Firestore integration, DataStore
├── model/ # User, BloodGroup, etc.
├── ui/ # Screens & Components
│ ├── screens/
│ └── components/
├── viewmodel/ # ViewModels for state logic
└── MainActivity.kt # App entry point


---


## 🔄 How It Works

1. **Login/Register** with name, department, session, blood group, contact number
2. **User data saved to Firebase Firestore**
3. **Home screen** shows a list of donors filtered by blood group (fetched from Firestore)
4. **Profile screen** displays your saved info and donation status
5. **Logout** clears DataStore and returns to login

---

## 🔮 Future Plans

- 📍 Location-based donor search
- 📢 Notification for urgent requests (FCM)
- 🧾 Donation history archive (per month)
- Admin panel for managing users
- Dark mode support

---

## 🙌 Developed By

**Kushick Chakraborty**  
🎓 ICT Department, Cumilla University  
📞 Contact: 01862179520  
📧 cbkushick@gmail.com

---

