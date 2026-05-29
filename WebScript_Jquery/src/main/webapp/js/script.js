$(document).ready(function() {
  function initDraggable() {
    $(".card").draggable({
      revert: "invalid",
      helper: "original",
      appendTo: "body",
    });
  }

  $(".column").droppable({
    accept: ".card",
    drop: function(event, ui) {
      $(this).append(ui.draggable);
      ui.draggable.css({top: 0, left: 0}); // 위치 초기화
    }
  });

  // 카드 추가
  $('#addBtn').on('click', function() {
    const type = $('#type').val();
    const title = $('#title').val();
    const desc = $('#description').val();

    if (!title) return alert("제목을 입력하세요.");

    const card = `
            <div class="card ${type}">
                <h4>${title}</h4>
                <p>${desc}</p>
                <button class="delete-btn">삭제</button>
            </div>
        `;
    $('#BACKLOG').append(card);
    initDraggable();
  });

  // 카드 삭제
  $(document).on('click', '.delete-btn', function() {
    $(this).closest('.card').remove();
  });

  // 전체 삭제
  $('#clearAll').on('click', function() {
    $('.card').remove();
  });
});