async function fetchSuggestions(query) {
    if (query.length === 0) {
        document.getElementById('suggestions').innerHTML = '';
        return;
    }

    try {
        const response = await fetch(`/api/suggestions?query=${encodeURIComponent(query)}`);
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const suggestions = await response.json();
        if (!suggestions || typeof suggestions !== 'object') {
            throw new Error('Invalid response format');
        }

        const combinedSuggestions = combineAndFilterSuggestions(suggestions);
        displaySuggestions(combinedSuggestions);
    } catch (error) {
        console.error('Fetch suggestions failed:', error);
        document.getElementById('suggestions').innerHTML = '<p>Error fetching suggestions</p>';
    }
}

function combineAndFilterSuggestions(suggestions) {
    const tinhSuggestions = suggestions.tinhSuggestions || [];
    const thanhPhoSuggestions = suggestions.thanhPhoSuggestions || [];
    const suggestionMap = new Map();

    tinhSuggestions.forEach(tinh => {
        suggestionMap.set(tinh.tenTinh, tinh);
    });

    thanhPhoSuggestions.forEach(thanhPho => {
        suggestionMap.set(thanhPho.tenTP, thanhPho);
    });

    return Array.from(suggestionMap.values());
}

function displaySuggestions(suggestions) {
    const suggestionBox = document.getElementById('suggestions');
    suggestionBox.innerHTML = ''; // Clear old suggestions

    if (suggestions.length > 0) {
        suggestions.forEach(location => {
            const option = document.createElement('div');
            const displayText = location.tenTinh ? location.tenTinh : location.tenTP;
            option.textContent = displayText;
            option.className = 'suggestion';
            option.onclick = function() {
                document.getElementById('location-input').value = displayText;
                suggestionBox.innerHTML = ''; // Clear suggestions after selection
            };
            suggestionBox.appendChild(option);
        });
        suggestionBox.style.display = 'block'; // Show the suggestion box if there are suggestions
    } else {
        suggestionBox.style.display = 'none'; // Hide the suggestion box if there are no suggestions
    }
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('location-input').addEventListener('input', function(e) {
        const inputVal = e.target.value.trim(); // Trim whitespace
        if (inputVal.length > 2) {
            fetchSuggestions(inputVal);
        } else {
            document.getElementById('suggestions').innerHTML = ''; // Clear suggestion box if input length is not sufficient
        }
    });

    document.getElementById('dangkyBtn').addEventListener('click', checkLogin);
    function checkLogin(event) {
        event.preventDefault(); // Prevent the default redirect action
        toastr.options = {
            "closeButton": true,
            "positionClass": "toast-bottom-right",
            "progressBar": true,
            "timeOut": "3000", // 3s
        };
        fetch('/api/check-login')
            .then(response => response.json())
            .then(isLoggedIn => {
                if (!isLoggedIn) {
                    // Show a notification using Toastr.js
                    toastr.error('Vui lòng đăng nhập để đăng ký Tour.');

                } else {
                    // User is logged in, proceed with the default action (redirect)
                    window.location.href = event.target.href;
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
});
