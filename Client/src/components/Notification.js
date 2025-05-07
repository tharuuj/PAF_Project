import React, { useEffect, useState } from 'react';
import axios from 'axios';

const NotificationsTab = () => {
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const deleteNotification = async (id) => {
    try {
      await axios.delete(`/api/v1/notifications/${id}`, {
        headers: {
          Authorization: localStorage.getItem("psnToken"),
        },
      });
      // Remove the deleted notification from the local state
      setNotifications((prev) => prev.filter((n) => n.id !== id));
    } catch (err) {
      console.error("Failed to delete notification:", err);
      alert("Failed to delete notification.");
    }
  };
  
  const markAsRead = async (id) => {
    try {
      await axios.put(`/api/v1/notifications/${id}/read`, null, {
        headers: {
          Authorization: localStorage.getItem("psnToken"),
        },
      });
  
      // Update the notification in local state
      setNotifications((prev) =>
        prev.map((n) =>
          n.id === id ? { ...n, read: true } : n
        )
      );
    } catch (err) {
      console.error("Failed to mark notification as read:", err);
      alert("Failed to mark as read.");
    }
  };
  


  // Replace with your actual API endpoint
  const fetchNotifications = async () => {
    try {
      setLoading(true);
      //send user id in request body
        const userId = localStorage.getItem("psnUserId");
        const response = await axios.post('/api/v1/notifications/user?userId=' + userId, null, {
          headers: {
            Authorization: localStorage.getItem("psnToken"),
          },
        });
        
         setNotifications(response.data);
    } catch (err) {
      setError(err.response?.data?.message || err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchNotifications();
  }, []);

  const renderNotification = (notification) => {
    const {
      id,
      senderFullname,
      type,
      message,
      createdAt,
      read,
    } = notification;

    const formattedDate = new Date(createdAt).toLocaleString();

    let icon = '';
    switch (type) {
      case 'LIKE':
        icon = '❤️';
        break;
      case 'COMMENT':
        icon = '💬';
        break;
      case 'REPLY':
        icon = '↩️';
        break;
      default:
        icon = '🔔';
    }

    return (
      <div
        key={id}
        style={{
          padding: '10px',
          borderBottom: '1px solid #ddd',
          backgroundColor: read ? '#fff' : '#f0f8ff',
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
        }}
      >
        <div>
          <div style={{ fontWeight: read ? 'normal' : 'bold' }}>
            {icon} {senderFullname}: {message}
          </div>
          <div style={{ fontSize: '0.85em', color: '#555' }}>{formattedDate}</div>
        </div>
        <div>
          {!read && (
            <button
              onClick={() => markAsRead(id)}
              style={{
                marginRight: '10px',
                backgroundColor: '#1890ff',
                color: 'white',
                border: 'none',
                borderRadius: '4px',
                padding: '4px 8px',
                cursor: 'pointer',
              }}
            >
              Mark as Read
            </button>
          )}
          <button
            onClick={() => deleteNotification(id)}
            style={{
              backgroundColor: '#ff4d4f',
              color: 'white',
              border: 'none',
              borderRadius: '4px',
              padding: '4px 8px',
              cursor: 'pointer',
            }}
          >
            Delete
          </button>
        </div>
      </div>
    );
    
    
  };

  if (loading) return <div>Loading notifications...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div style={{ maxHeight: '400px', overflowY: 'auto' }}>
      {notifications.length === 0 ? (
        <div>No notifications yet.</div>
      ) : (
        notifications.map(renderNotification)
      )}
    </div>
  );
};

export default NotificationsTab;
